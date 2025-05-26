```toml
name = 'Create Loan'
method = 'POST'
url = 'http://127.0.0.1:8080/v1/loans'
sortWeight = 1000000
id = '0e288fb3-c602-40a2-970b-a09110373a40'

[body]
type = 'JSON'
raw = '''
{
  "customerId": 1,
  "amount": 1200.5
}'''
```
