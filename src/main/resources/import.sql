-- Categories
INSERT INTO public.category (range, type) VALUES ('[0,10]', 'RANGE');
INSERT INTO public.category (range, type) VALUES ('(10,100]', 'RANGE');
INSERT INTO public.category (range, type) VALUES ('(100,)', 'RANGE');
INSERT INTO public.category (range, type) VALUES ('[0,1)', 'TX_DATA');
INSERT INTO public.category (range, type) VALUES ('[1,2)', 'TX_DATA');
INSERT INTO public.category (range, type) VALUES ('[2,3]', 'TX_DATA');
INSERT INTO public.category (range, type) VALUES ('[54,54]', 'TX_DATA');

-- Tecnologies
 INSERT INTO public.tecnology(name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('Zigbee Global', 2, 0, 2.4, '802.15.4', 65527, 10, 0.25) ;
 INSERT INTO public.tecnology(name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('Bluetooth Class 1', 1, 20, 2.4, '802.15.1', 8, 100, 3) ;
 INSERT INTO public.tecnology(name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('Bluetooth Class 2', 1, 4, 2.4, '802.15.1', 8, 10, 2) ;
 INSERT INTO public.tecnology(name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('Bluetooth Class 3', 1, 0, 2.4, '802.15.1', 8, 8, 1) ;
 INSERT INTO public.tecnology(name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('Bluetooth Smart (BLE) 1', 2, 10, 2.4, '', 999999999, 50, 2) ;
 INSERT INTO public.tecnology(name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('Bluetooth Smart (BLE) 1.5', 2, -20, 2.4, '', 999999999, 50, 1) ;
 INSERT INTO public.tecnology(name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('WIFI 2.4Ghz', 22, NULL, 2.4, '802.11a/b/g', 2007, 100, 54) ;
 INSERT INTO public.tecnology(name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('WIFI 5Ghz', 22, NULL, 5, '802.11a/b/g', 2007, 100, 54) ;
 INSERT INTO public.tecnology(name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('LoraWAN', 0.125, 20, NULL, '', NULL, 15000, 0.25) ;
 INSERT INTO public.tecnology(name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('GPRS', 0.2, 33, 1, '', NULL, NULL, NULL) ;
 INSERT INTO public.tecnology(name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('3G/4G', NULL, NULL, NULL, '', NULL, NULL, NULL) ;
 INSERT INTO public.tecnology(name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('Wibree', NULL, 10, 2.4, '', NULL, 10, 1) ;
 INSERT INTO public.tecnology(name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('Narrow Band', 0.18, 20, NULL, '', NULL, 20000, 0.127) ;
 INSERT INTO public.tecnology(name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('Sigfox', 0.001, 14, 0.9, '', NULL, 40000, NULL) ;
 INSERT INTO public.tecnology(name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('WirelessHART', 2, 10, 2.4, '802.15.4-2006', NULL, 200, 0.25) ;
 INSERT INTO public.tecnology(name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('Bluetooth Smart (BLE) 2', 2, -20, 2.4, '', 999999999, 50, 0.5) ;
 INSERT INTO public.tecnology(name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('Bluetooth Smart (BLE) 3', 2, -20, 2.4, '', 999999999, 50, 0.125) ;