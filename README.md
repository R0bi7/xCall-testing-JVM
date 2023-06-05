<p align="center">
  <img 
    src="./icon_img.png" 
    alt="Icon logo">
</p>

# JAVA SCORE starter boilerplate

A repo for boilerplate code for testing, deploying and shipping Java Smart Contracts on Icon blockchain.

## Requirements

You need to install JDK 11 or later version. Visit [OpenJDK.net](http://openjdk.java.net/) for prebuilt binaries.
Or you can install a proper OpenJDK package from your OS vendors.
You will also need `jq`.

Please find below the recommanded commands to run to install these packages:

- In macOS:

```bash
$ # Install brew
$ /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
$ # Install JDK
$ brew tap AdoptOpenJDK/openjdk
$ brew cask install adoptopenjdk11
$ # Install JQ
$ brew install jq
```

- In Windows (Windows 10 or later)

You can install the JDK11 natively, but you will need `bash` in order to run the HelloWorld deploy scripts.
Please [install WSL](https://docs.microsoft.com/en-us/windows/wsl/install-manual) first, so you can follow the same instructions than Linux.

- In Linux (Ubuntu 18.04):

```bash
$ # Install JDK
$ sudo apt install openjdk-11-jdk
$ # Install JQ
$ sudo apt install jq
```

## How to Build and Deploy the project

### 1. Build the project (mostly for making sure that everything works correctly for your setup)

`./gradlew build`

or

`./gradlew clean build` (for subsequent clean builds)

This should run all the unittest, and run successfully with a similar output than below:

```java
Starting a Gradle Daemon (subsequent builds will be faster)

> Task :Tests:customIrc2Token-Test:test
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

BUILD SUCCESSFUL in 2s
52 actionable tasks: 52 executed
```

### 2. Setup build system

You'll need python3 and pip installed on your machine beforehand.

Now we can install the virtualenv  and the dependencies:

```bash
$ # Run this in the ./scripts folder of the project
$ python -m venv ./venv
$ source ./venv/bin/activate
$ pip install -r ./requirements.txt
```

Everytime you want to use the build & deploy system, please do `source ./venv/bin/activate` inside `./scripts` beforehand.

### 3. Keystore config

In order to deploy/update the contract keystore and password must be configured as following:
- Put keystore content in "scripts/config/keystores/{network}/operator.icx"
- Put keystore password in "scripts/config/keystores/{network}/operator.pwd"

**Note**: `{network}` should be replaced with network name you are deploying/updating to!

Make sure you have enough ICX in wallet!

### 4. Deploying contracts

#### HelloWorld contract

```bash
$ # Run this in the root folder of the project
$ # We specify "lisbon" as an argument here, which means the contracts
$ # will be deployed on Lisbon. You can set it to "custom" too for the
$ # custom network
$ ./scripts/scenario/1.deploy_hello_world.sh lisbon
[...]
# This should end with the following message
[🎉] HelloWorld have been successfully deployed!
```

#### CustomIrc2Token contract

```bash
$ # Run this in the root folder of the project
$ # We specify "lisbon" as an argument here, which means the contracts
$ # will be deployed on Lisbon. You can set it to "custom" too for the
$ # custom network
$ ./scripts/scenario/2.deploy_custom_irc2_token.sh lisbon
[...]
# This should end with the following message
[🎉] CustomIrc2Token have been successfully deployed!
```

### 5. Updating contracts

#### HelloWorld contract

```bash
$ # Run this in the root folder of the project
$ # We specify "lisbon" as an argument here, which means the contracts
$ # will be updated on Lisbon. You can set it to "custom" too for the
$ # custom network
$ ./scripts/scenario/1.1.update_hello_world.sh lisbon
[...]
# This should end with the following message
[🎉] HelloWorld have been successfully updated!
```

#### CustomIrc2Token contract

```bash
$ # Run this in the root folder of the project
$ # We specify "lisbon" as an argument here, which means the contracts
$ # will be updated on Lisbon. You can set it to "custom" too for the
$ # custom network
$ ./scripts/scenario/2.1.update_custom_irc2_token.sh lisbon
[...]
# This should end with the following message
[🎉] CustomIrc2Token have been successfully updated!
```