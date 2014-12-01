import xmlrpclib,MySQLdb

db = MySQLdb.connect('localhost', 'root', 'dynamickool', 'mybook')
cursor = db.cursor()
def search(email):
    list_data = []
    sql = "select * from phonebook where email = '%s'" % email;
    cursor.execute(sql)
    results=cursor.fetchall()
    print results
    ''' 
    for row in results:
        list.append(row[0])    
        list.append(row[1])
        list.append(row[2])   
        list.append(row[3])   
        list.append(row[4])  
        list.append(row[5])
    return list_data
    '''
            
search('dip.kush08@gmail.com')
 
