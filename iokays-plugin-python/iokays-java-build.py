import subprocess
import os
import paramiko
from scp import SCPClient
import yaml

# 1. 加载配置文件
config_path = os.path.join(os.path.dirname(__file__), "common-config.yml")
with open(config_path, 'r') as f:
    config = yaml.safe_load(f)

# 2. 获取本地配置
local_java = config['local']['java']
target_dir = local_java['project_url']
gradle_path = local_java['gradle_url']

# 3. 获取服务器配置
ftp_config = config['ftp']
server_info = {
    'hostname': ftp_config['host'],
    'port': ftp_config.get('port', 22),
    'username': ftp_config['user'],
    'password': ftp_config['password']
}

# 4. 切换到项目目录
print(f"切换到目录: {target_dir}")
os.chdir(target_dir)

# 5. 执行gradle构建
print("执行gradle构建...")
subprocess.run([gradle_path, "clean", "build", "-x", "Test"], check=True)

# 6. 定义JAR文件映射
module_jars = {
    "iokays-plugin-h2": "iokays-plugin-h2.jar",
    "iokays-authorizationserver": "iokays-authorizationserver.jar",
    "iokays-dispatch": "iokays-dispatch.jar",
    "iokays-flowable": "iokays-flowable.jar",
    "iokays-ai": "iokays-ai.jar",
    "iokays-ai-mcp-server": "iokays-ai-mcp-server.jar"
}

# 7. SSH上传和部署
ssh = None
scp = None
try:
    # 建立连接
    ssh = paramiko.SSHClient()
    ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    ssh.connect(**server_info)
    scp = SCPClient(ssh.get_transport())

    # 上传所有JAR文件
    print("开始上传JAR文件...")
    for module, jar in module_jars.items():
        local_path = os.path.join(module, "build", "libs", jar)
        if os.path.exists(local_path):
            remote_path = f"/opt/iokays/iokays-java/{jar}"
            print(f"上传 {local_path}...")
            scp.put(local_path, remote_path)
        else:
            print(f"! 未找到 {local_path}")

#     # 后台启动服务
#     print("启动远程服务...")
#     stdin, stdout, stderr = ssh.exec_command("python3 /opt/iokays/iokays-java-run.py")
#     print(stdout.read().decode())
#     if stderr.read().decode():
#         print("错误:", stderr.read().decode())

    print("所有操作已完成!")

except Exception as e:
    print(f"错误发生: {str(e)}")

finally:
    # 清理连接
    if scp: scp.close()
    if ssh: ssh.close()
