import sys
import time

from NetworkTableManager import NetworkTableClient

if __name__ == "__main__":
    client = NetworkTableClient(sys.argv[1], "My Client")
    client.connect()

    while client.is_connected():
        print("connected")
        time.sleep(0.1)

    client.terminate()
