# Rapport d'Initiation MongoDB

## envirenement windows

### I - Création d’un compte et déploiement d’un cluster

![006-initiation-mongoDB-cluster-dashboard](/006-initiation-mongoDB/006-initiation-mongoDB-cluster-dashboard.png "MongoDB Custer Dashboard")

### II - Connexion à MongoDB Atlas

```text
> mongosh "mongodb+srv://<username>:<password>@<cluster-name>.nmvmncp.mongodb.net" --apiVersion 1
```

```text
Current Mongosh Log ID: 69876de2b6d27254de1e2620
Connecting to:          mongodb+srv://<credentials>@<cluster-name>.nmvmncp.mongodb.net/?appName=mongosh+2.5.10
Using MongoDB:          8.0.19 (API Version 1)
Using Mongosh:          2.5.10
mongosh 2.6.0 is available for download: https://www.mongodb.com/try/download/shell

For mongosh info see: https://www.mongodb.com/docs/mongodb-shell/

Atlas atlas-c5f6uy-shard-0 [primary] test>
```

### III - Création d’une base de données et d’une collection

```text
Atlas atlas-c5f6uy-shard-0 [primary] test> use big-data
```

#### 1 - Pour se placer dans une base

```text
switched to db big-data
```

#### 2 - Créer une collection nommée users

```text
Atlas atlas-c5f6uy-shard-0 [primary] big-data> db.createCollection("users")
```

```js
{ ok: 1 }
```

#### 3 - Lister les collections de la base

```text
Atlas atlas-c5f6uy-shard-0 [primary] big-data> show collections
```

```text
users
```

#### 4 - Insérer un document JSON dans la collection users

```text
Atlas atlas-c5f6uy-shard-0 [primary] big-data> db.users.insertOne( { name: "Mohamed", age: 20, city: "Casablanca" })
```

```js
{
  acknowledged: true,
  insertedId: ObjectId('69876efbb6d27254de1e2621')
}
```

#### 5 - Insérer maintenant plusieurs tuples dans la collection users

```text
Atlas atlas-c5f6uy-shard-0 [primary] big-data> db.users.insertMany([
... { name: "Alice", age: 25, city: "Paris" },
... { name: "Bob", age: 30, city: "Lyon" },
... { name: "Charlie", age: 28, city: "Marseille" }
... ])
```

```js
{
  acknowledged: true,
  insertedIds: {
    '0': ObjectId('69876f23b6d27254de1e2622'),
    '1': ObjectId('69876f23b6d27254de1e2623'),
    '2': ObjectId('69876f23b6d27254de1e2624')
  }
}
```

### IV - Requêtes pour récupérer des données

#### 1 - L’objet (javascript) implicite, db, permet de soumettre des demandes d’exécution de certaines méthodes. Afficher tous les utilisateurs de la collection

```text
Atlas atlas-c5f6uy-shard-0 [primary] big-data> db.users.find()
```

```js
[
  {
    _id: ObjectId('69876efbb6d27254de1e2621'),
    name: 'Mohamed',
    age: 20,
    city: 'Casablanca'
  },
  {
    _id: ObjectId('69876f23b6d27254de1e2622'),
    name: 'Alice',
    age: 25,
    city: 'Paris'
  },
  {
    _id: ObjectId('69876f23b6d27254de1e2623'),
    name: 'Bob',
    age: 30,
    city: 'Lyon'
  },
  {
    _id: ObjectId('69876f23b6d27254de1e2624'),
    name: 'Charlie',
    age: 28,
    city: 'Marseille'
  }
]
```

#### 2 - insérer un autre document avec les information de votre choix

```text
Atlas atlas-c5f6uy-shard-0 [primary] big-data> db.users.insertMany([
...   { name: "Damien", age: 22, city: "Toulouse" },
...   { name: "Élodie", age: 34, city: "Nice" },
...   { name: "Fabien", age: 29, city: "Nantes" },
...   { name: "Géraldine", age: 41, city: "Strasbourg" },
...   { name: "Hugo", age: 27, city: "Montpellier" },
...   { name: "Isabelle", age: 31, city: "Bordeaux" },
...   { name: "Julien", age: 24, city: "Lille" },
...   { name: "Karine", age: 38, city: "Rennes" },
...   { name: "Léo", age: 21, city: "Reims" },
...   { name: "Manon", age: 26, city: "Saint-Étienne" },
...   { name: "Nicolas", age: 33, city: "Le Havre" },
...   { name: "Océane", age: 28, city: "Toulon" },
...   { name: "Pierre", age: 45, city: "Grenoble" },
...   { name: "Quentin", age: 23, city: "Dijon" },
...   { name: "Roxane", age: 30, city: "Angers" },
...   { name: "Sébastien", age: 37, city: "Villeurbanne" },
...   { name: "Théo", age: 19, city: "Nîmes" },
...   { name: "Ursule", age: 32, city: "Aix-en-Provence" }
... ])
```

```js
{
  acknowledged: true,
  insertedIds: {
    '0': ObjectId('698770b8b6d27254de1e2625'),
    '1': ObjectId('698770b8b6d27254de1e2626'),
    '2': ObjectId('698770b8b6d27254de1e2627'),
    '3': ObjectId('698770b8b6d27254de1e2628'),
    '4': ObjectId('698770b8b6d27254de1e2629'),
    '5': ObjectId('698770b8b6d27254de1e262a'),
    '6': ObjectId('698770b8b6d27254de1e262b'),
    '7': ObjectId('698770b8b6d27254de1e262c'),
    '8': ObjectId('698770b8b6d27254de1e262d'),
    '9': ObjectId('698770b8b6d27254de1e262e'),
    '10': ObjectId('698770b8b6d27254de1e262f'),
    '11': ObjectId('698770b8b6d27254de1e2630'),
    '12': ObjectId('698770b8b6d27254de1e2631'),
    '13': ObjectId('698770b8b6d27254de1e2632'),
    '14': ObjectId('698770b8b6d27254de1e2633'),
    '15': ObjectId('698770b8b6d27254de1e2634'),
    '16': ObjectId('698770b8b6d27254de1e2635'),
    '17': ObjectId('698770b8b6d27254de1e2636')
  }
}
```

#### 3 - Compter le nombre de documents dans la collection

```text
Atlas atlas-c5f6uy-shard-0 [primary] big-data> db.users.count()
```

```text
DeprecationWarning: Collection.count() is deprecated. Use countDocuments or estimatedDocumentCount.
22
```

```text
Atlas atlas-c5f6uy-shard-0 [primary] big-data> db.users.countDocuments()
```

```js
22
```

#### 4 - Récupérer un utilisateur spécifique

```text
Atlas atlas-c5f6uy-shard-0 [primary] big-data> db.users.findOne({ name: "Alice" })
```

```js
{
  _id: ObjectId('69876f23b6d27254de1e2622'),
  name: 'Alice',
  age: 25,
  city: 'Paris'
}
```

#### 5 - Filtrer les utilisateurs par âge (age>20)

```text
Atlas atlas-c5f6uy-shard-0 [primary] big-data> db.users.find({age:{$gt:20}})
```

```js
[
  {
    _id: ObjectId('69876f23b6d27254de1e2622'),
    name: 'Alice',
    age: 25,
    city: 'Paris'
  },
  {
    _id: ObjectId('69876f23b6d27254de1e2623'),
    name: 'Bob',
    age: 30,
    city: 'Lyon'
  },
  {
    _id: ObjectId('69876f23b6d27254de1e2624'),
    name: 'Charlie',
    age: 28,
    city: 'Marseille'
  },
  {
    _id: ObjectId('698770b8b6d27254de1e2625'),
    name: 'Damien',
    age: 22,
    city: 'Toulouse'
  },
  {
    _id: ObjectId('698770b8b6d27254de1e2626'),
    name: 'Élodie',
    age: 34,
    city: 'Nice'
  },
  {
    _id: ObjectId('698770b8b6d27254de1e2627'),
    name: 'Fabien',
    age: 29,
    city: 'Nantes'
  },
  {
    _id: ObjectId('698770b8b6d27254de1e2628'),
    name: 'Géraldine',
    age: 41,
    city: 'Strasbourg'
  },
  {
    _id: ObjectId('698770b8b6d27254de1e2629'),
    name: 'Hugo',
    age: 27,
    city: 'Montpellier'
  },
  {
    _id: ObjectId('698770b8b6d27254de1e262a'),
    name: 'Isabelle',
    age: 31,
    city: 'Bordeaux'
  },
  {
    _id: ObjectId('698770b8b6d27254de1e262b'),
    name: 'Julien',
    age: 24,
    city: 'Lille'
  },
  {
    _id: ObjectId('698770b8b6d27254de1e262c'),
    name: 'Karine',
    age: 38,
    city: 'Rennes'
  },
  {
    _id: ObjectId('698770b8b6d27254de1e262d'),
    name: 'Léo',
    age: 21,
    city: 'Reims'
  },
  {
    _id: ObjectId('698770b8b6d27254de1e262e'),
    name: 'Manon',
    age: 26,
    city: 'Saint-Étienne'
  },
  {
    _id: ObjectId('698770b8b6d27254de1e262f'),
    name: 'Nicolas',
    age: 33,
    city: 'Le Havre'
  },
  {
    _id: ObjectId('698770b8b6d27254de1e2630'),
    name: 'Océane',
    age: 28,
    city: 'Toulon'
  },
  {
    _id: ObjectId('698770b8b6d27254de1e2631'),
    name: 'Pierre',
    age: 45,
    city: 'Grenoble'
  },
  {
    _id: ObjectId('698770b8b6d27254de1e2632'),
    name: 'Quentin',
    age: 23,
    city: 'Dijon'
  },
  {
    _id: ObjectId('698770b8b6d27254de1e2633'),
    name: 'Roxane',
    age: 30,
    city: 'Angers'
  },
  {
    _id: ObjectId('698770b8b6d27254de1e2634'),
    name: 'Sébastien',
    age: 37,
    city: 'Villeurbanne'
  },
  {
    _id: ObjectId('698770b8b6d27254de1e2636'),
    name: 'Ursule',
    age: 32,
    city: 'Aix-en-Provence'
  }
]
```

#### 6 - Modifier l’âge d’un utilisateur

```text
Atlas atlas-c5f6uy-shard-0 [primary] big-data> db.users.updateOne({ name: "Alice" }, { $set: { age: 26 } })
```

```js
{
  acknowledged: true,
  insertedId: null,
  matchedCount: 1,
  modifiedCount: 1,
  upsertedCount: 0
}
```

#### 7 - Supprimer un utilisateur

```text
Atlas atlas-c5f6uy-shard-0 [primary] big-data> db.users.deleteOne({ name: "Charlie" })
```

```js
{ 
    acknowledged: true, 
    deletedCount: 1 
}
```

#### 8 - supprimer une collection

```text
Atlas atlas-c5f6uy-shard-0 [primary] big-data> db.users.drop()
```

```js
true
```

### V - Importation de fichier dans une collection

```text
> mongoimport --uri "mongodb+srv://<username>:<password>@<cluster-name>.nmvmncp.mongodb.net/BankDB" --collection transactions --file transactions.json --jsonArray
```

```text
2026-02-10T17:33:36.753+0100    connected to: mongodb+srv://[**REDACTED**]@<cluster-name>.nmvmncp.mongodb.net/BankDB
2026-02-10T17:33:39.592+0100    1000 document(s) imported successfully. 0 document(s) failed to import.
```

```text
Atlas atlas-c5f6uy-shard-0 [primary] test> use BankDB
```

```text
switched to db BankDBs
```

```text
Atlas atlas-c5f6uy-shard-0 [primary] BankDB> show collections
```

```text
transactions
```

```text
Atlas atlas-c5f6uy-shard-0 [primary] BankDB> db.transactions.findOne()
```

```js
{
  _id: ObjectId('698b5de0733d44d2c62f4b7b'),
  'Transaction ID': 'TXN9743166792',
  'Sender Account ID': 'ACC82066',
  'Receiver Account ID': 'ACC33985',
  'Transaction Amount': 579.65,
  'Transaction Type': 'Transfer',
  Timestamp: '2025-01-17 10:17:00',
  'Transaction Status': 'Failed',
  'Fraud Flag': 'False',
  'Device Used': 'Mobile',
  'Network Slice ID': 'Slice2',
  'Latency (ms)': 14,
  'Slice Bandwidth (Mbps)': 246,
  'PIN Code': 9680,
  Geolocation: { 
    type: 'Point', 
    coordinates: [ -0.1278, 51.5074 ] 
  }
}
```

```text
Atlas atlas-c5f6uy-shard-0 [primary] BankDB> db.transactions.countDocuments()
```

```js
1000
```

```text
Atlas atlas-c5f6uy-shard-0 [primary] BankDB> db.transactions.find({ "Transaction Status": "Failed" })
```

```js
[
  {
    _id: ObjectId('698b5de0733d44d2c62f4f48'),
    'Transaction ID': 'TXN7066384355',
    'Sender Account ID': 'ACC45635',
    'Receiver Account ID': 'ACC93414',
    'Transaction Amount': 381.94,
    'Transaction Type': 'Withdrawal',
    Timestamp: '2025-01-17 10:22:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'False',
    'Device Used': 'Desktop',
    'Network Slice ID': 'Slice3',
    'Latency (ms)': 8,
    'Slice Bandwidth (Mbps)': 94,
    'PIN Code': 9531,
    Geolocation: { type: 'Point', coordinates: [ -37.6173, 34.0522 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4f49'),
    'Transaction ID': 'TXN3386767631',
    'Sender Account ID': 'ACC59588',
    'Receiver Account ID': 'ACC63706',
    'Transaction Amount': 656.49,
    'Transaction Type': 'Deposit',
    Timestamp: '2025-01-17 10:36:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'True',
    'Device Used': 'Desktop',
    'Network Slice ID': 'Slice3',
    'Latency (ms)': 8,
    'Slice Bandwidth (Mbps)': 126,
    'PIN Code': 7015,
    Geolocation: { type: 'Point', coordinates: [ -139.6917, 51.5074 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4f4c'),
    'Transaction ID': 'TXN9728180847',
    'Sender Account ID': 'ACC83660',
    'Receiver Account ID': 'ACC31355',
    'Transaction Amount': 872.85,
    'Transaction Type': 'Transfer',
    Timestamp: '2025-01-17 10:01:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'False',
    'Device Used': 'Desktop',
    'Network Slice ID': 'Slice1',
    'Latency (ms)': 17,
    'Slice Bandwidth (Mbps)': 150,
    'PIN Code': 5968,
    Geolocation: { type: 'Point', coordinates: [ 118.2437, 34.0522 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4f4d'),
    'Transaction ID': 'TXN2136436539',
    'Sender Account ID': 'ACC85339',
    'Receiver Account ID': 'ACC40432',
    'Transaction Amount': 514.1,
    'Transaction Type': 'Deposit',
    Timestamp: '2025-01-17 10:04:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'True',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice3',
    'Latency (ms)': 14,
    'Slice Bandwidth (Mbps)': 120,
    'PIN Code': 9226,
    Geolocation: { type: 'Point', coordinates: [ -37.6173, 51.5074 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4f4f'),
    'Transaction ID': 'TXN3785811199',
    'Sender Account ID': 'ACC45754',
    'Receiver Account ID': 'ACC56026',
    'Transaction Amount': 507.32,
    'Transaction Type': 'Deposit',
    Timestamp: '2025-01-17 10:57:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'True',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice3',
    'Latency (ms)': 18,
    'Slice Bandwidth (Mbps)': 142,
    'PIN Code': 1424,
    Geolocation: { type: 'Point', coordinates: [ -37.6173, 55.7558 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4f50'),
    'Transaction ID': 'TXN3610926310',
    'Sender Account ID': 'ACC64812',
    'Receiver Account ID': 'ACC14886',
    'Transaction Amount': 333.81,
    'Transaction Type': 'Withdrawal',
    Timestamp: '2025-01-17 10:21:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'True',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice3',
    'Latency (ms)': 18,
    'Slice Bandwidth (Mbps)': 131,
    'PIN Code': 1649,
    Geolocation: { type: 'Point', coordinates: [ -139.6917, 34.0522 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4f55'),
    'Transaction ID': 'TXN7250272568',
    'Sender Account ID': 'ACC49715',
    'Receiver Account ID': 'ACC10486',
    'Transaction Amount': 59.57,
    'Transaction Type': 'Withdrawal',
    Timestamp: '2025-01-17 10:31:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'False',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice3',
    'Latency (ms)': 11,
    'Slice Bandwidth (Mbps)': 78,
    'PIN Code': 7280,
    Geolocation: { type: 'Point', coordinates: [ -37.6173, 35.6895 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4f56'),
    'Transaction ID': 'TXN4930471112',
    'Sender Account ID': 'ACC28678',
    'Receiver Account ID': 'ACC21230',
    'Transaction Amount': 1485.37,
    'Transaction Type': 'Withdrawal',
    Timestamp: '2025-01-17 10:02:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'False',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice2',
    'Latency (ms)': 13,
    'Slice Bandwidth (Mbps)': 149,
    'PIN Code': 3093,
    Geolocation: { type: 'Point', coordinates: [ -2.3522, 55.7558 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4f58'),
    'Transaction ID': 'TXN9210129507',
    'Sender Account ID': 'ACC98981',
    'Receiver Account ID': 'ACC73310',
    'Transaction Amount': 558.64,
    'Transaction Type': 'Deposit',
    Timestamp: '2025-01-17 10:30:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'False',
    'Device Used': 'Desktop',
    'Network Slice ID': 'Slice1',
    'Latency (ms)': 4,
    'Slice Bandwidth (Mbps)': 204,
    'PIN Code': 4036,
    Geolocation: { type: 'Point', coordinates: [ 74.006, 48.8566 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4f59'),
    'Transaction ID': 'TXN2679491739',
    'Sender Account ID': 'ACC69673',
    'Receiver Account ID': 'ACC60940',
    'Transaction Amount': 919.35,
    'Transaction Type': 'Deposit',
    Timestamp: '2025-01-17 10:52:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'False',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice1',
    'Latency (ms)': 17,
    'Slice Bandwidth (Mbps)': 210,
    'PIN Code': 8981,
    Geolocation: { type: 'Point', coordinates: [ -37.6173, 48.8566 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4f5a'),
    'Transaction ID': 'TXN6073923771',
    'Sender Account ID': 'ACC31273',
    'Receiver Account ID': 'ACC75160',
    'Transaction Amount': 1285.8,
    'Transaction Type': 'Withdrawal',
    Timestamp: '2025-01-17 10:17:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'True',
    'Device Used': 'Desktop',
    'Network Slice ID': 'Slice2',
    'Latency (ms)': 7,
    'Slice Bandwidth (Mbps)': 87,
    'PIN Code': 4938,
    Geolocation: { type: 'Point', coordinates: [ -2.3522, 34.0522 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4f5d'),
    'Transaction ID': 'TXN2215717837',
    'Sender Account ID': 'ACC95972',
    'Receiver Account ID': 'ACC50750',
    'Transaction Amount': 483.36,
    'Transaction Type': 'Withdrawal',
    Timestamp: '2025-01-17 11:00:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'True',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice2',
    'Latency (ms)': 12,
    'Slice Bandwidth (Mbps)': 56,
    'PIN Code': 1009,
    Geolocation: { type: 'Point', coordinates: [ -37.6173, 55.7558 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4f61'),
    'Transaction ID': 'TXN3992032184',
    'Sender Account ID': 'ACC16789',
    'Receiver Account ID': 'ACC21980',
    'Transaction Amount': 495.36,
    'Transaction Type': 'Transfer',
    Timestamp: '2025-01-17 10:02:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'False',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice2',
    'Latency (ms)': 5,
    'Slice Bandwidth (Mbps)': 155,
    'PIN Code': 9888,
    Geolocation: { type: 'Point', coordinates: [ 74.006, 55.7558 ] }
  }
]
```

```text
Atlas atlas-c5f6uy-shard-0 [primary] BankDB> db.transactions.find({ "Fraud Flag": "True" })
```

```js
[
  {
    _id: ObjectId('698b5de0733d44d2c62f4b7d'),
    'Transaction ID': 'TXN2214150284',
    'Sender Account ID': 'ACC48650',
    'Receiver Account ID': 'ACC76457',
    'Transaction Amount': 1129.88,
    'Transaction Type': 'Transfer',
    Timestamp: '2025-01-17 10:56:00',
    'Transaction Status': 'Success',
    'Fraud Flag': 'True',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice3',
    'Latency (ms)': 10,
    'Slice Bandwidth (Mbps)': 127,
    'PIN Code': 6374,
    Geolocation: { type: 'Point', coordinates: [ 74.006, 34.0522 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4b82'),
    'Transaction ID': 'TXN4229370499',
    'Sender Account ID': 'ACC11531',
    'Receiver Account ID': 'ACC53580',
    'Transaction Amount': 683.48,
    'Transaction Type': 'Transfer',
    Timestamp: '2025-01-17 10:55:00',
    'Transaction Status': 'Success',
    'Fraud Flag': 'True',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice1',
    'Latency (ms)': 19,
    'Slice Bandwidth (Mbps)': 213,
    'PIN Code': 4495,
    Geolocation: { type: 'Point', coordinates: [ -2.3522, 34.0522 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4b84'),
    'Transaction ID': 'TXN8954205071',
    'Sender Account ID': 'ACC49428',
    'Receiver Account ID': 'ACC33014',
    'Transaction Amount': 154.78,
    'Transaction Type': 'Transfer',
    Timestamp: '2025-01-17 10:41:00',
    'Transaction Status': 'Success',
    'Fraud Flag': 'True',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice3',
    'Latency (ms)': 7,
    'Slice Bandwidth (Mbps)': 68,
    'PIN Code': 8997,
    Geolocation: { type: 'Point', coordinates: [ -37.6173, 48.8566 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4b86'),
    'Transaction ID': 'TXN4247571145',
    'Sender Account ID': 'ACC60921',
    'Receiver Account ID': 'ACC11419',
    'Transaction Amount': 933.24,
    'Transaction Type': 'Deposit',
    Timestamp: '2025-01-17 10:25:00',
    'Transaction Status': 'Success',
    'Fraud Flag': 'True',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice3',
    'Latency (ms)': 20,
    'Slice Bandwidth (Mbps)': 191,
    'PIN Code': 8375,
    Geolocation: { type: 'Point', coordinates: [ -37.6173, 55.7558 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4b87'),
    'Transaction ID': 'TXN4822238918',
    'Sender Account ID': 'ACC27604',
    'Receiver Account ID': 'ACC93416',
    'Transaction Amount': 276.76,
    'Transaction Type': 'Withdrawal',
    Timestamp: '2025-01-17 10:54:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'True',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice1',
    'Latency (ms)': 14,
    'Slice Bandwidth (Mbps)': 101,
    'PIN Code': 1771,
    Geolocation: { type: 'Point', coordinates: [ -2.3522, 55.7558 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4b8a'),
    'Transaction ID': 'TXN7417651729',
    'Sender Account ID': 'ACC26106',
    'Receiver Account ID': 'ACC21043',
    'Transaction Amount': 1371.8,
    'Transaction Type': 'Transfer',
    Timestamp: '2025-01-17 10:09:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'True',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice1',
    'Latency (ms)': 8,
    'Slice Bandwidth (Mbps)': 61,
    'PIN Code': 3326,
    Geolocation: { type: 'Point', coordinates: [ -37.6173, 35.6895 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4b8b'),
    'Transaction ID': 'TXN7538176865',
    'Sender Account ID': 'ACC40510',
    'Receiver Account ID': 'ACC11454',
    'Transaction Amount': 604.25,
    'Transaction Type': 'Transfer',
    Timestamp: '2025-01-17 10:56:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'True',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice1',
    'Latency (ms)': 5,
    'Slice Bandwidth (Mbps)': 129,
    'PIN Code': 9633,
    Geolocation: { type: 'Point', coordinates: [ -37.6173, 55.7558 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4b8c'),
    'Transaction ID': 'TXN5126223597',
    'Sender Account ID': 'ACC49116',
    'Receiver Account ID': 'ACC36754',
    'Transaction Amount': 826.75,
    'Transaction Type': 'Withdrawal',
    Timestamp: '2025-01-17 10:18:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'True',
    'Device Used': 'Desktop',
    'Network Slice ID': 'Slice2',
    'Latency (ms)': 15,
    'Slice Bandwidth (Mbps)': 232,
    'PIN Code': 2547,
    Geolocation: { type: 'Point', coordinates: [ -2.3522, 40.7128 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4b8d'),
    'Transaction ID': 'TXN6302601632',
    'Sender Account ID': 'ACC91042',
    'Receiver Account ID': 'ACC98945',
    'Transaction Amount': 129.78,
    'Transaction Type': 'Transfer',
    Timestamp: '2025-01-17 10:11:00',
    'Transaction Status': 'Success',
    'Fraud Flag': 'True',
    'Device Used': 'Desktop',
    'Network Slice ID': 'Slice1',
    'Latency (ms)': 19,
    'Slice Bandwidth (Mbps)': 106,
    'PIN Code': 5199,
    Geolocation: { type: 'Point', coordinates: [ 118.2437, 51.5074 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4b8e'),
    'Transaction ID': 'TXN9520068950',
    'Sender Account ID': 'ACC14994',
    'Receiver Account ID': 'ACC16656',
    'Transaction Amount': 495.9,
    'Transaction Type': 'Deposit',
    Timestamp: '2025-01-17 10:14:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'True',
    'Device Used': 'Desktop',
    'Network Slice ID': 'Slice3',
    'Latency (ms)': 10,
    'Slice Bandwidth (Mbps)': 179,
    'PIN Code': 3075,
    Geolocation: { type: 'Point', coordinates: [ 74.006, 34.0522 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4b95'),
    'Transaction ID': 'TXN3109277527',
    'Sender Account ID': 'ACC48227',
    'Receiver Account ID': 'ACC93536',
    'Transaction Amount': 1135.8,
    'Transaction Type': 'Deposit',
    Timestamp: '2025-01-17 10:56:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'True',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice2',
    'Latency (ms)': 11,
    'Slice Bandwidth (Mbps)': 69,
    'PIN Code': 8607,
    Geolocation: { type: 'Point', coordinates: [ 118.2437, 48.8566 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4b96'),
    'Transaction ID': 'TXN6355066290',
    'Sender Account ID': 'ACC14006',
    'Receiver Account ID': 'ACC27800',
    'Transaction Amount': 485.37,
    'Transaction Type': 'Transfer',
    Timestamp: '2025-01-17 10:50:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'True',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice3',
    'Latency (ms)': 20,
    'Slice Bandwidth (Mbps)': 173,
    'PIN Code': 9631,
    Geolocation: { type: 'Point', coordinates: [ -37.6173, 34.0522 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4b9a'),
    'Transaction ID': 'TXN2169752734',
    'Sender Account ID': 'ACC43792',
    'Receiver Account ID': 'ACC51105',
    'Transaction Amount': 72.97,
    'Transaction Type': 'Withdrawal',
    Timestamp: '2025-01-17 10:25:00',
    'Transaction Status': 'Success',
    'Fraud Flag': 'True',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice2',
    'Latency (ms)': 5,
    'Slice Bandwidth (Mbps)': 216,
    'PIN Code': 4347,
    Geolocation: { type: 'Point', coordinates: [ -2.3522, 55.7558 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4b9b'),
    'Transaction ID': 'TXN2088036535',
    'Sender Account ID': 'ACC66635',
    'Receiver Account ID': 'ACC27770',
    'Transaction Amount': 884.55,
    'Transaction Type': 'Transfer',
    Timestamp: '2025-01-17 10:04:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'True',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice2',
    'Latency (ms)': 14,
    'Slice Bandwidth (Mbps)': 228,
    'PIN Code': 9368,
    Geolocation: { type: 'Point', coordinates: [ -37.6173, 51.5074 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4ba1'),
    'Transaction ID': 'TXN2956253396',
    'Sender Account ID': 'ACC98407',
    'Receiver Account ID': 'ACC76557',
    'Transaction Amount': 499.18,
    'Transaction Type': 'Withdrawal',
    Timestamp: '2025-01-17 10:31:00',
    'Transaction Status': 'Success',
    'Fraud Flag': 'True',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice1',
    'Latency (ms)': 19,
    'Slice Bandwidth (Mbps)': 231,
    'PIN Code': 8697,
    Geolocation: { type: 'Point', coordinates: [ 74.006, 55.7558 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4ba5'),
    'Transaction ID': 'TXN4231576514',
    'Sender Account ID': 'ACC33069',
    'Receiver Account ID': 'ACC45185',
    'Transaction Amount': 944.51,
    'Transaction Type': 'Withdrawal',
    Timestamp: '2025-01-17 10:53:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'True',
    'Device Used': 'Desktop',
    'Network Slice ID': 'Slice3',
    'Latency (ms)': 18,
    'Slice Bandwidth (Mbps)': 228,
    'PIN Code': 4036,
    Geolocation: { type: 'Point', coordinates: [ -0.1278, 34.0522 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4ba6'),
    'Transaction ID': 'TXN7131992962',
    'Sender Account ID': 'ACC25089',
    'Receiver Account ID': 'ACC78786',
    'Transaction Amount': 992.8,
    'Transaction Type': 'Withdrawal',
    Timestamp: '2025-01-17 10:43:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'True',
    'Device Used': 'Desktop',
    'Network Slice ID': 'Slice1',
    'Latency (ms)': 11,
    'Slice Bandwidth (Mbps)': 139,
    'PIN Code': 7140,
    Geolocation: { type: 'Point', coordinates: [ -139.6917, 34.0522 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4ba8'),
    'Transaction ID': 'TXN6614511449',
    'Sender Account ID': 'ACC14564',
    'Receiver Account ID': 'ACC10609',
    'Transaction Amount': 326.66,
    'Transaction Type': 'Withdrawal',
    Timestamp: '2025-01-17 10:41:00',
    'Transaction Status': 'Success',
    'Fraud Flag': 'True',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice1',
    'Latency (ms)': 3,
    'Slice Bandwidth (Mbps)': 161,
    'PIN Code': 8229,
    Geolocation: { type: 'Point', coordinates: [ 74.006, 34.0522 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4bab'),
    'Transaction ID': 'TXN3244921303',
    'Sender Account ID': 'ACC69633',
    'Receiver Account ID': 'ACC88988',
    'Transaction Amount': 434.31,
    'Transaction Type': 'Withdrawal',
    Timestamp: '2025-01-17 10:56:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'True',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice2',
    'Latency (ms)': 18,
    'Slice Bandwidth (Mbps)': 67,
    'PIN Code': 8452,
    Geolocation: { type: 'Point', coordinates: [ 74.006, 51.5074 ] }
  },
  {
    _id: ObjectId('698b5de0733d44d2c62f4bac'),
    'Transaction ID': 'TXN4437479248',
    'Sender Account ID': 'ACC45830',
    'Receiver Account ID': 'ACC66726',
    'Transaction Amount': 480.29,
    'Transaction Type': 'Withdrawal',
    Timestamp: '2025-01-17 10:32:00',
    'Transaction Status': 'Failed',
    'Fraud Flag': 'True',
    'Device Used': 'Mobile',
    'Network Slice ID': 'Slice2',
    'Latency (ms)': 7,
    'Slice Bandwidth (Mbps)': 84,
    'PIN Code': 3773,
    Geolocation: { type: 'Point', coordinates: [ 118.2437, 51.5074 ] }
  }
]
Type "it" for more
```

```text
Atlas atlas-c5f6uy-shard-0 [primary] BankDB> db.transactions.aggregate([
... { $group: { _id: "$Transaction Status", total: { $count: {} } } }
... ])
```

```js
[ { _id: 'Success', total: 487 }, { _id: 'Failed', total: 513 } ]
```
