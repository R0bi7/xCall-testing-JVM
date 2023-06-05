#!/bin/bash

set -e

source ./scripts/venv/bin/activate

source ./scripts/util/get_address.sh
source ./scripts/util/dir.sh
source ./scripts/util/console.sh
source ./scripts/util/env.sh
source ./scripts/util/get_wallet_address.sh

source ./scripts/pkg.sh

# Network must be given as a parameter of this script
if [ "$#" -ne "1" ] ; then
  error "Usage: $0 <network>"
  exit 1
fi

network=$1

# Start
info "Deploying hello-world..."

# Package information
pkg=$(getHelloWorldPkg)
javaPkg="Contracts:hello-world"
build="optimized"

# Setup packages
setupJavaDir ${javaPkg} ${build}
setupDeployDir ${pkg} ${network}
setupCallsDir ${pkg} ${network}
deployDir=$(getDeployDir ${pkg} ${network})
callsDir=$(getCallsDir ${pkg} ${network})

# Deploy on ICON network
name="Protokol7"

filter=$(cat <<EOF
{
  name: \$name,
}
EOF
)

jq -n \
  --arg name "${name}" \
  "${filter}" > ${deployDir}/params.json

./scripts/run.py -e ${network} deploy ${pkg}
