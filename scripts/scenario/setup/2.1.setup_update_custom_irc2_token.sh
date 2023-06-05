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
info "Updating customIrc2Token..."

# Package information
pkg=$(getHelloWorldPkg)
javaPkg="Contracts:customIrc2Token"
build="optimized"

# Setup packages
setupJavaDir ${javaPkg} ${build}
setupDeployDir ${pkg} ${network}
setupCallsDir ${pkg} ${network}
deployDir=$(getDeployDir ${pkg} ${network})

# Deploy on ICON network
_name="customIrc2Token"
_symbol="customIrc2Token"
_decimals="0x12"

filter=$(cat <<EOF
{
  _name: \$_name,
  _symbol: \$_symbol,
  _decimals: \$_decimals
}
EOF
)

jq -n \
  --arg _name "${_name}" \
  --arg _symbol "${_symbol}" \
  --arg _decimals "${_decimals}" \
  "${filter}" > ${deployDir}/params.json

./scripts/run.py -e ${network} update ${pkg}
