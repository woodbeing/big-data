# configuration SWITCH C3560-24P Rabat

cli

```text
Switch> enable
Switch# configure terminal
Switch(config)# hostname <your-host-name>
Core-Switch(config)# vtp domain MTI
Core-Switch(config)# vtp mode server
Core-Switch(config)# vtp password MTI2026
Core-Switch(config)# vlan 100
Core-Switch(config-vlan)# name Production
Core-Switch(config-vlan)# exit
Core-Switch(config)# vlan 200
Core-Switch(config-vlan)# name Management
Core-Switch(config-vlan)# exit
Core-Switch(config)# spanning-tree vlan 100 root primary
Core-Switch(config)# spanning-tree vlan 200 root primary
Core-Switch(config)# interface range gigabitEthernet 0/1-2
Core-Switch(config-if-range)# switchport trunk encapsulation dot1q
Core-Switch(config-if-range)# switchport mode trunk
Core-Switch(config-if-range)# switchport trunk native vlan 200
Core-Switch(config-if-range)# no shutdown
Core-Switch(config-if-range)# exit
Core-Switch(config)# interface range fastEthernet 0/1-24
Core-Switch(config-if-range)# switchport trunk encapsulation dot1q
Core-Switch(config-if-range)# switchport mode trunk
Core-Switch(config-if-range)# switchport trunk native vlan 200
Core-Switch(config-if-range)# no shutdown
Core-Switch(config-if-range)# exit
Core-Switch(config)# end
```
