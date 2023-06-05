# Copyright 2021 ICONation, 2023 Protokol7
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

from iconsdk.libs.in_memory_zip import gen_deploy_data_content

from config import Config
import json
import os

from meta import get_meta
from util import die
from util.promptHandler import query_yes_no


def print_empty(*args):
    pass

def get_deploy(package, endpoint):
    deploy_path = f"./scripts/config/deploy/{package}/"
    deploy = json.loads(open(f"{deploy_path}/{endpoint}/deploy.json", "r").read())
    return deploy['scoreAddress']

def get_params(package, endpoint):
    deploy_path = f"./scripts/config/deploy/{package}/"
    params = json.loads(open(f"{deploy_path}/{endpoint}/params.json", "r").read())
    return params
def is_non_zero_file(fpath: str) -> bool:
    return os.path.isfile(fpath) and os.path.getsize(fpath) > 0
def deploy(config: Config, package: str, verbose=False):
    print(config.endpoint)
    owner = config.owner
    tx_handler = config.tx_handler
    
    params = get_params(package, config.endpoint)
    javaPkg, version, build = get_meta(package, config.endpoint)
    print(f"javaPkg = {javaPkg}")
    javaPkgPath = javaPkg.replace(':', '/')
    print(f"javaPkgPath = {javaPkg}")
    jarName = javaPkg.split(':')[-1]

    if version:
        content = gen_deploy_data_content(f"./{javaPkgPath}/build/libs/{jarName}-{version}-{build}.jar")
    else:
        content = gen_deploy_data_content(f"./{javaPkgPath}/build/libs/{jarName}-{build}.jar")

    content_type = 'application/java'
    deploy_path = f"./scripts/config/deploy/{package}/"
    deploy_file_path = f"{deploy_path}/{config.endpoint}/deploy.json"

    override_flag = True
    # Make sure user confirms override of an existing deployment
    if is_non_zero_file(deploy_file_path):
        override_flag = query_yes_no("Deployment exists. Are you sure you want to override an existing deployment?")

    if override_flag:
        print(f"Deploying {javaPkg} contract to {config.endpoint} ...")
        # Deploy SCORE using tx handler
        tx_hash = tx_handler.install(owner, content, content_type, params)

        # Ensure SCORE was successfully deployed
        tx_result = tx_handler.ensure_tx_result(tx_hash, verbose)

        open(deploy_file_path, "w+").write(json.dumps(tx_result, indent=2))
        score_address = tx_result["scoreAddress"]
        print(f"Deployed at {score_address}")
        return score_address
    else:
        die('Error: Deployment cancelled due to an existing deployment (override cancelled)')

def update(config: Config, package: str, verbose=False):
    owner = config.owner
    tx_handler = config.tx_handler

    javaPkg, version, build = get_meta(package, config.endpoint)
    javaPkgPath = javaPkg.replace(':', '/')
    jarName = javaPkg.split(':')[-1]
    address = get_deploy(package, config.endpoint)
    params = get_params(package, config.endpoint)

    if version:
        content = gen_deploy_data_content(f"./{javaPkgPath}/build/libs/{jarName}-{version}-{build}.jar")
    else:
        content = gen_deploy_data_content(f"./{javaPkgPath}/build/libs/{jarName}-{build}.jar")

    content_type = 'application/java'
    print(f"Updating {javaPkg} contract {address} ...")
    tx_hash = tx_handler.update(owner, address, content, content_type, params)

    tx_result = tx_handler.ensure_tx_result(tx_hash, verbose)
    deploy_path = f"./scripts/config/deploy/{package}/"
    open(f"{deploy_path}/{config.endpoint}/deploy.json", "w+").write(json.dumps(tx_result, indent=2))
    score_address = tx_result["scoreAddress"]
    print(f"Updated at {score_address} \ntxHash={tx_hash}")
    return score_address
