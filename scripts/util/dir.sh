#!/bin/bash

set -e

CONFIG_DIR=./scripts/config
DEPLOY_DIR=${CONFIG_DIR}/deploy
CALLS_DIR=${CONFIG_DIR}/calls

getDeployDir () {
  local pkg=$1
  local network=$2
  local deployDir=${DEPLOY_DIR}/${pkg}/${network}
  echo ${deployDir}
}

setupDeployDir () {
  local pkg=$1
  local network=$2
  local deployDir=$(getDeployDir ${pkg} ${network})
  mkdir -p ${deployDir}
}

getCallsDir () {
  local pkg=$1
  local network=$2
  local callsDir=${CALLS_DIR}/${pkg}/${network}
  echo ${callsDir}
}

setupCallsDir () {
  local pkg=$1
  local network=$2
  local callsDir=$(getCallsDir ${pkg} ${network})
  mkdir -p ${callsDir}
}

getJavaDir () {
  local javaPkg=$1
  local build=$2
  local javaDir=${DEPLOY_DIR}/${pkg}
  echo ${javaDir}
}

setupJavaDir () {
  local javaPkg=$1
  local build=$2
  local javaDir=$(getJavaDir ${javaPkg} ${build})
  mkdir -p ${javaDir}

  jq -n \
    --arg javaPkg $javaPkg \
    --arg build $build \
  '{javaPkg: $javaPkg, build: $build}' > ${javaDir}/build.json
}
