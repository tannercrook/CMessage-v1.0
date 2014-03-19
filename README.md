CMessage-v1.0
=============

System Analysis and Design Project Program

Created by TannerCrook and Team. 2014.

Purpose
===========

The purpose of this application is for the sharing and collaboration of code. As a team we found
the most possible way of implementing was by using FTP. We chose this because:

1. We could accomplish with our current resources (servers, etc).
2. Implementation by others could be easy and custom.
3. It successfully filled the requirements for the project.

Security
=========

SFTP was not possible do to our personal resources. However, one could easily implement SFTP
by simply changing all "FTP" calls to "SFTP" and using the correct login info corresponding 
to their server. 

We solved the problem by implementing and using the JASYPT library for encryption. The file is 
encrypted on save to file, and remains encrypted until after download on next host. This also 
protects the integrity of file on FTP server. This makes backups secure as well.
