# One-Way Hash Functions and Message Authentication Codes (MAC)

This repository documents the work completed for a university lab on cryptographic primitives, specifically focusing on one-way hash functions and message authentication codes (MACs). The lab explored practical applications using command-line tools and custom Java programs.

---

## Task 1: Generating Message Digests

A UTF-8 encoded file was created containing a KTH email address in the format `username@kth.se`.

Message digests were generated for the file using the following one-way hash algorithms:
- MD5
- SHA1
- SHA256

The `openssl dgst` tool was used as follows:

```bash
openssl dgst -md5 file.txt
openssl dgst -sha1 file.txt
openssl dgst -sha256 file.txt

## Observations

The output lengths differ between algorithms: MD5 produces 128-bit hashes, SHA1 produces 160-bit, and SHA256 produces 256-bit.
All three digests differ completely in content, even though they were computed over the same input.
SHA256 is significantly more resistant to collisions due to its longer hash length and stronger design.

## Task 2: Keyed Hash and HMAC

Keyed hashes (HMACs) were generated using the `-hmac` option of `openssl dgst`, which, although undocumented, is supported. HMACs were computed using the MD5, SHA1, and SHA256 hash functions.

```bash
openssl dgst -md5 -hmac "secret" file.txt
openssl dgst -sha1 -hmac "secret" file.txt
openssl dgst -sha256 -hmac "secret" file.txt

## Task 3: Randomness of One-Way Hash Functions

To examine the avalanche effect of cryptographic hash functions:

1.  A hash value H1 was computed for the original file.
2.  The first bit of the file was flipped using a binary editor (`ghex`).
3.  A new hash value H2 was computed for the modified file.
4.  A comparison was performed between H1 and H2.

**Observations**

Only a single bit change in the input file led to vastly different hash outputs.
A short program was written to count the number of identical bits between H1 and H2.
For both MD5 and SHA256, the number of common bits was very low, confirming strong avalanche properties.

## Task 4: Collision Resistance

To explore weak collision resistance:

1.  The `MessageDigest` class from `java.security` was used.
2.  Hash values were truncated to 24 bits to make brute-force attempts feasible.
3.  Collisions were searched using a brute-force approach for the following messages:
    * IV1013 security
    * Security is fun
    * Yes, indeed
    * Secure IV1013
    * No way

**Observations**

The number of trials needed to find a collision varied across inputs.
On average, collisions were found after approximately $2^{12}$ to $2^{13}$ attempts.
The feasibility of the attack demonstrates the importance of using full-length hash values for real-world applications.
