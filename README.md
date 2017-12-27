# README

本demo是串口收发数据demo

其中通过Application类中的getSerialPort方法获取某个单个端口实例

```
mSerialPort = mApplication.getSerialPort();
mOutputStream = mSerialPort.getOutputStream();
mInputStream = mSerialPort.getInputStream();
```

通过输入输出流进行串口接收跟发送

```
mSerialPort = new SerialPort(new File(device), baudrate, 0);
```

可以通过传入串口节点不同来操作不同节点的输入输出，即发送和接收。

类似`device='/dev/ttyUSB0'`

## License

遵循 Apache License 2.0许可证。有关详细,请参阅 [LICENSE](https://github.com/lieber68/AndroidSerialPort/blob/master/LICENSE)。

