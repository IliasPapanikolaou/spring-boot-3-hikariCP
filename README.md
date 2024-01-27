### Request Samples:

Save  Student: POST request: localhost:8080/api/student
```json
{
    "firstname": "John",
    "lastname": "Doe",
    "grade": "6th",
    "age": 18
}
```

```shell
for ($i = 1; $i -le 100; $i++) {
    Write-Host "Iteration $i"
    curl http://localhost:8080/api/student
    # Pause for 1 second
    Start-Sleep -Seconds 1
}
```