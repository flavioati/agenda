#sudo systemctl status mariadb
#sudo systemctl status tomcat
ip=$(ip -4 addr show eth0 | grep -oP '(?<=inet\s)\d+(\.\d+){3}')
tomcat_port="8080"
mariadb_port="3306" # grep -w 'mysql' /etc/services
echo "tomcat=${ip}:${tomcat_port}"
echo "mariadb=${ip}:${mariadb_port}"
