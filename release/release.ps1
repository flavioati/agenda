# Usage: .\release.ps1 <VERSION>
cp ..\deploy\deploy_fedora_wsl.sh .
cp ..\deploy\get_service_info.sh .
$version = $args[0]
$file = "agenda-v${version}.tar.gz"
echo $file
tar -czvf $file .\agenda.war .\deploy_fedora_wsl.sh .\get_service_info.sh
rm *.sh
rm agenda.war
