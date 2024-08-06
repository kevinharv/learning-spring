from faker import Faker
import random
import json
import requests
import requests.auth

QTY_GROUPS = 10
QTY_USERS = 100
BASE_URL = "http://localhost:8080"
USERNAME = "kevin"
PASSWORD = "Testing123"

auth = requests.auth.HTTPBasicAuth(USERNAME, PASSWORD)
fake = Faker(locale=["en_US"])

# Create groups
for i in range(QTY_GROUPS):
    group = {"name": fake.company(), "description": "Security group."}
    response = requests.post(url=f"{BASE_URL}/groups", headers={"Content-Type": "application/json"}, auth=(USERNAME, PASSWORD), data=json.dumps(group))
    print(f"[GROUP] {response.status_code}")

# Create users
for i in range(QTY_USERS):
    first_name = fake.first_name()
    last_name = fake.last_name()
    domain = fake.domain_name()

    user = {
        "employeeId": fake.credit_card_number(),
        "firstName": first_name,
        "middleName": fake.name(),
        "lastName": last_name,
        "userPrincipalName": f"{first_name[0]}{last_name}@{domain}",
        "preferredEmailAddress": f"{first_name}.{last_name}@{domain}",
        "personalEmailAddress": f"{first_name}{last_name[0]}@{fake.domain_name()}",
        "workPhone": fake.phone_number(),
        "personalPhone": fake.phone_number(),
        "adUser": {
            "dn": f"CN={first_name[0]}{last_name},OU=Users,DC=example,DC=COM",
            "sAMAccountName": f"{first_name[0]}{last_name}",
        },
    }

    groupNum = random.randrange(1, QTY_GROUPS + 1, step=1)

    response = requests.post(url=f"{BASE_URL}/users", headers={"Content-Type": "application/json"}, auth=auth, data=json.dumps(user))
    print(f"[USER] {response.status_code}")
    data = response.json()
    user_id = data["id"]

    response = requests.post(
        url=f"{BASE_URL}/users/{user_id}/addGroup?groupID={groupNum}", headers={"Content-Type": "application/json"}, auth=auth
    )
    print(f"[GROUP ADD USER] {response.status_code}")
