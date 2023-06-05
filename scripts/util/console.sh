#!/bin/bash

set -e

function error {
    >&2 echo -ne "\e[91m"
    >&2 echo -e  "🚫 ${1}"
    >&2 echo -ne "\e[39m"
}

function info {
    >&2 echo -ne "\e[32m"
    >&2 echo -e  "⌛ ${1}"
    >&2 echo -ne "\e[39m"
}

function done {
    >&2 echo -ne "\e[32m"
    >&2 echo -e  "✅ ${1}"
    >&2 echo -ne "\e[39m"
}

function warning {
    >&2 echo -ne "\e[93m"
    >&2 echo -e  "⚠️ ${1}"
    >&2 echo -ne "\e[39m"
}

function debug {
    >&2 echo -ne "\e[94m"
    >&2 echo -e  "🐛 ${1}"
    >&2 echo -ne "\e[39m"
}

function highlight {
    >&2 echo -ne "\e[96m"
    >&2 echo -e  "🔦 ${1}"
    >&2 echo -ne "\e[39m"
}

function success {
    >&2 echo -ne "\e[96m"
    >&2 echo -e  "[🎉] ${1}"
    >&2 echo -ne "\e[39m"
}