# Example Files Task 1

This directory contains the following files:

* plain.txt - File with plaintext data (text)
* key.txt - File with an encryption key, as a decimal integer string
* cipher.data - File with ciphertext data (binary)

The file `cipher.data` is the output obtained by using `plain.txt` as
input file for the StreamCipher program from Task 1, with the key from
the file `key.txt` as key. To check that your StreamCipher program
produces the correct output you could for example run the following
command (using bash shell):

```
$ java StreamCiphher --key $(<key.txt) --in plain.txt --out cipher-test.data
```

Then check that the content of `cipher-test.data` is the same as `cipher.data`:

```
$ cmp cipher-test.data cipher.data
```

Alternatively, `plain.txt` can be obtained by using `cipher.data` as
input file for StreamCipher. You should also check that `plain.txt` is
created correctly using `cipher.data` as input:

```
$ java StreamCiphher --key $(<key.txt) --in cipher.data --out plain-test.txt
$ cmp plain-test.txt plain.txt
```
