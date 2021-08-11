# RSA-Encryptor
A spriing boot rest service that encrypts clear text in RSA/ECB/OAEPWithSHA-256AndMGF1Padding format.

# HOW TO USE

curl -v -H "Content-Type: application/json" -d '{"publicKey": "QUpDam9vYTU2Yk5IYk1ReGF6bUl6NHF6QVpxX2cxYllISUZGbVdIOXplZkx4aW1OQ2lodUxDNGcwM2JrcHR4UUFDUWtqaU9OY3ZHWk5jUG5XRVl
jel9XR29ZS0Nyc0lidzRJWXA0dnB1NjVzZ3BEeWsyZ0Z1b3VQVVFobHBZYm1temVBSGVXdVBldTV4dl83X25GMFlDNkx2RlJYc1FtZmRaSTBmS0JZbkpzXzVOOGxSWEhteUk0UFQ3LXVldFRYOEdpNXE2LTN4WW55VkFtTDZnVkNDNi1DU
EpLXzFMM01teFJoZC1qMWsxWGlnTVRNSkp2WDhmY2ZRSHV3VmduVlZyTHdGT081QlZaYkxtRGpZbEdmZWpBeDhXSFZlcmpyd3h3OG15X1Y3UktPc1ZEdldxZnpIY0NKV05KMjNUTWNWQ1Vsc3RqdjFRRG5vZlZRZHltRlFRVQ==" , "da
ta": "sdpkwmfpwmefpwfwfpociwefpiwf"}' http://localhost:8080/api/v1/encrypt/


# NB

public key must be base64 Encoded.


