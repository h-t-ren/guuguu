准备工作：

a）安装Java SDK 1.6+
b) 安装Maven3.0.5 (高版本的有bug)
c）修改host文件windows/system32/etc/drivers/host
203.208.46.146 www.google.com
203.208.46.147 www.google.com.hk
203.208.46.132 clients1.google.com
203.208.46.149 mail.google.com
203.208.46.161 chatenabled.mail.google.com
203.208.46.161 mail-attachment.googleusercontent.com

74.125.135.136 dl-ssl.google.com

1) 下载STS （eclipse for spring）
   http://spring.io/tools/sts

2) 下载android sdk
   http://developer.android.com/sdk/index.html
   设置 ANDROID_HOME 和path
   export ANDROID_HOME=<path to Android SDK>
   PATH=$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools:$ANDROID_HOME/platforms:$PATH


3）安装android for eclipse
  http://developer.android.com/sdk/installing/installing-adt.html
