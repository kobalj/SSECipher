# SSECipher
A simple Java implementation of SSECipher encoding/decoding library

[![Build Status](https://travis-ci.org/kobalj/SSECipher.svg?branch=master)](https://travis-ci.org/kobalj/SSECipher)
[![Coverage Status](https://coveralls.io/repos/github/kobalj/SSECipher/badge.svg?branch=master)](https://coveralls.io/github/kobalj/SSECipher?branch=master)
[![License](https://img.shields.io/badge/license-GPLv3-blue.svg)](LICENSE)

## Overview
The library is intended for encoding and decoding of passwords with AES cipher. 
In addition to that it contains a simple runner for generating random encryption/decryption keys.
For the library to work you need to make sure you have [Java Cryptography Extension (JCE) Unlimited Strength](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html) on the classpath.

The key should never be part of the code in which the library is used, but provided through environment parameters or via a configuration file.
The library is not intended for encrypting one way passwords (for example login credentials). For that other ways should be used (hash + salt). The library is intended to encrypt/decrypt strings where we needed the original one but don't want to store the plain text string itself.

## Using the runner

The runner currently support the following operations:
- check for supported ciphers
- generate AES key with optional key size as argument

To use it, you have to call the jar:

```
java -cp SSECipher-1.0.0.jar si.kobalj.ssecipher.runner.CCommandLine <arguments>
```

All message output is printed to standard output.

## Code Example

As first, get the library from maven central:

```xml
<dependency>
    <groupId>si.kobalj.utilities</groupId>
    <artifactId>SSECipher</artifactId>
    <version>1.0.0</version>
</dependency>
```
To use it you need to generate a base64 encoded key to encrypt/decrypt strings.

After that, generate the SSECipher class via its builder. At the moment only AES is supported.
In the builder We set the base64 encoded key, define to use padding or no padding and set 
the mode (CBC or ECB).

```Java
ISSECipher cipher = new CAESCipherBuilder()
                .setBase64Key("xJWkeHio3VfzjyL8yOgZZ6f44q/6tz66eF5Jta+SKVc=")
                .usePadding(true)
                .setMode(CAESCipherBuilder.Mode.CipherBlockChaining).build();
```

Now we just use encrypt/decrypt methods when we need to encrypt or decrypt a string.

```Java
// Encrypt password
String encPass = cipher.encrypt(pass);

// Decrypt enrypted password
String decPass = cipher.decrypt(encPass);
```

## Licence

Licensed under the [GPLv3 license](LICENSE).