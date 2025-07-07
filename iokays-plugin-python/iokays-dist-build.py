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
local_frontend = config['local']['frontend']
target_dir = local_frontend['project_url']
npm_path = local_frontend.get('npm_path', 'npm')  # 默认为npm

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

# 5. 执行npm构建
print("执行npm构建...")
subprocess.run([npm_path, "run", "build"], check=True)

# 6. 定义需要上传的文件夹
upload_folders = [
    "iokays-dist",  # 主前端构建目录
    "iokays-nginx"  # nginx配置目录
]

# 7. SSH上传和部署
ssh = None
scp = None
try:
    # 建立连接
    ssh = paramiko.SSHClient()
    ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    ssh.connect(**server_info)
    scp = SCPClient(ssh.get_transport())

    # 上传所有文件夹到固定路径 /opt/iokays/
    print("开始上传前端文件到 /opt/iokays/...")
    for folder in upload_folders:
        if os.path.exists(folder):
            print(f"上传 {folder}...")

            # 递归上传整个文件夹到固定远程路径
            remote_base = "/opt/iokays"
            for root, dirs, files in os.walk(folder):
                for file in files:
                    local_file = os.path.join(root, file)
                    # 保持原有目录结构，但基于固定远程路径
                    relative_path = os.path.relpath(local_file, folder)
                    remote_file = os.path.join(remote_base, os.path.basename(folder), relative_path)
                    remote_dir = os.path.dirname(remote_file)

                    # 确保远程目录存在
                    ssh.exec_command(f"mkdir -p {remote_dir}")
                    scp.put(local_file, remote_file)
        else:
            print(f"! 未找到 {folder}")

    print("所有操作已完成!")

    # nginx -t -c /opt/iokays/iokays-nginx/nginx.conf
    # nginx -s reload -c /opt/iokays/iokays-nginx/nginx.conf

except Exception as e:
    print(f"错误发生: {str(e)}")
    raise

finally:
    # 清理连接
    if scp: scp.close()
    if ssh: ssh.close()
