#!/bin/bash

set -e

source ./scripts/util/console.sh

get_wallet_address () {
  local wallet=$1
    
  # Get the Fixed EOA address
  if [[ ! -f "${wallet}" ]]; then
    error "${wallet} not found"
    exit 1
  fi

  address=$(cat ${wallet} | jq .address -r)
  echo -ne ${address}
}