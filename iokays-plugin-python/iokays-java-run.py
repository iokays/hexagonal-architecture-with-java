import os
import time
import subprocess

os.chdir("/opt/iokays/iokays-java")

# 终止所有 iokays-*.jar 进程
subprocess.run(["pkill", "-f", "java.*iokays-"])

# subprocess.run(["pkill", "-f", "iokays-plugin-h2.jar"])
# subprocess.run(["pkill", "-f", "iokays-authorizationserver.jar"])
# subprocess.run(["pkill", "-f", "iokays-dispatch.jar"])
# subprocess.run(["pkill", "-f", "iokays-flowable.jar"])
# subprocess.run(["pkill", "-f", "iokays-ai.jar"])
# subprocess.run(["pkill", "-f", "iokays-ai-mcp-server.jar"])

time.sleep(10)

subprocess.Popen("nohup java -jar iokays-plugin-h2.jar > logs/h2.log 2>&1 &", shell=True)
subprocess.Popen("nohup java -jar iokays-authorizationserver.jar > logs/authorizationserver.log 2>&1 &", shell=True)
subprocess.Popen("nohup java -jar iokays-dispatch.jar > logs/quartz.log 2>&1 &", shell=True)
subprocess.Popen("nohup java -jar iokays-flowable.jar > logs/flowable.log 2>&1 &", shell=True)
subprocess.Popen("nohup java -jar iokays-ai.jar > logs/ai.log 2>&1 &", shell=True)
subprocess.Popen("nohup java -jar iokays-ai-mcp-server.jar > logs/mcp.log 2>&1 &", shell=True)