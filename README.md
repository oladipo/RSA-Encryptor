# RSA-Encryptor
A Spring Boot rest service that encrypts clear text in RSA/ECB/OAEPWithSHA-256AndMGF1Padding format.

# HOW TO USE

curl --location --request POST 'http://localhost:8080/api/v1/encrypt/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "publicKeyModulus": "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1kn4MmocoCXnuMeidWV4BjJTvLPxqLsGigzlRJ1a3JYbRIzmS5DvodT92xRoQav46DlmXV96KHtTb3US+6vNloV9rWbdH7JiQOSk4f8tk6BCpYp4aEP+1J8J5SfjnzrsJcF1lUnuxM+AAez44GLOWiDRwfsjU6XtXbXdN9cuCWQIDAQAB",
    "publicKeyExponent":"AQAB",
    "data": "qwerty"
}'

