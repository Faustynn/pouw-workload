import requests


def test_health(service):
    r = requests.get(service("workload", 8000) + "/health", timeout=10)
    assert r.status_code == 200
    assert r.json()["status"] == "UP"
