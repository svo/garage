databaseChangeLog() {
  changeSet(id: '0001', author: 'svo') {
    createTable(tableName: 'vehicle', remarks: '') {

      column(name: 'id', type: 'int', autoIncrement: 'true') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: 'vehicle_pk')
      }

      column(name: 'type', type: 'varchar(16)')
    }
  }
}
