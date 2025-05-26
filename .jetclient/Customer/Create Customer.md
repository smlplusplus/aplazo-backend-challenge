```toml
name = 'Create Customer'
method = 'POST'
url = 'http://127.0.0.1:8080/v1/customers'
sortWeight = 1000000
id = '9564f26a-a755-44f8-8c64-543e3975b5b6'

[auth]
type = 'NO_AUTH'

[body]
type = 'JSON'
raw = '''
{
  "firstName": "Samuel",
  "lastName": "Rivera",
  "secondLastName": "Hernandez",
  "dateOfBirth": "1990-01-15"
}'''
```
