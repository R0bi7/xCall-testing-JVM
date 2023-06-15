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
info "Deploying DAppProxySample..."

# Package information
pkg=$(getDAppProxySamplePkg)
javaPkg="Contracts:dapp-sample"
build="optimized"

# Setup packages
setupJavaDir ${javaPkg} ${build}
setupDeployDir ${pkg} ${network}
setupCallsDir ${pkg} ${network}
deployDir=$(getDeployDir ${pkg} ${network})

# Deploy on ICON network

# XCALL address
_callService="cxf4958b242a264fc11d7d8d95f79035e35b21c1bb"

filter=$(cat <<EOF
{
  _callService: \$_callService,
}
EOF
)

jq -n \
  --arg _callService "${_callService}" \
  "${filter}" > ${deployDir}/params.json

./scripts/run.py -e ${network} deploy ${pkg}
