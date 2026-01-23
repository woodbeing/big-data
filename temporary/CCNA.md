# configuration SWITCH C3560-24P Rabat

cli

```text
Switch> enable
Switch# configure terminal
Switch(config)# hostname <your-host-name>
<your-host-name>(config)# vtp domain MTI
<your-host-name>(config)# vtp mode server
<your-host-name>(config)# vtp password MTI2026
<your-host-name>(config)# vlan 100
<your-host-name>(config-vlan)# name Production
<your-host-name>(config-vlan)# exit
<your-host-name>(config)# vlan 200
<your-host-name>(config-vlan)# name Management
<your-host-name>(config-vlan)# exit
<your-host-name>(config)# spanning-tree vlan 100 root primary
<your-host-name>(config)# spanning-tree vlan 200 root primary
<your-host-name>(config)# interface range gigabitEthernet 0/1-2
<your-host-name>(config-if-range)# switchport trunk encapsulation dot1q
<your-host-name>(config-if-range)# switchport mode trunk
<your-host-name>(config-if-range)# switchport trunk native vlan 200
<your-host-name>(config-if-range)# no shutdown
<your-host-name>(config-if-range)# exit
<your-host-name>(config)# interface range fastEthernet 0/1-24
<your-host-name>(config-if-range)# switchport trunk encapsulation dot1q
<your-host-name>(config-if-range)# switchport mode trunk
<your-host-name>(config-if-range)# switchport trunk native vlan 200
<your-host-name>(config-if-range)# no shutdown
<your-host-name>(config-if-range)# exit
<your-host-name>(config)# end
```

```text
enable
```

```text
configure terminal
```

```text
hostname <your-host-name>
```

```text
vtp domain MTI
```

```text
vtp mode server
```

```text
vtp password MTI2026
```

```text
vlan 100
```

```text
name Production
```

```text
exit
```

```text
vlan 200
```

```text
name Management
```

```text
exit
```

```text
spanning-tree vlan 100 root primary
```

```text
spanning-tree vlan 200 root primary
```

```text
interface range gigabitEthernet 0/1-2
```

```text
switchport trunk encapsulation dot1q
```

```text
switchport mode trunk
```

```text
switchport trunk native vlan 200
```

```text
no shutdown
```

```text
exit
```

```text
interface range fastEthernet 0/1-24
```

```text
switchport trunk encapsulation dot1q
```

```text
switchport mode trunk
```

```text
switchport trunk native vlan 200
```

```text
no shutdown
```

```text
exit
```

```text
end
```
