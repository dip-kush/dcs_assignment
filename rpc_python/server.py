import xmlrpclib,MySQLdb
from SimpleXMLRPCServer import SimpleXMLRPCServer

db = MySQLdb.connect('localhost', 'root', 'dynamickool', 'mybook')

cursor = db.cursor()



def insert(firstname, lastname, phoneno, email, city, state):
    #cursor.execute('set autocommit = 1')
    sql = "insert into phonebook values('%s','%s','%s','%s','%s','%s');" % (firstname,lastname, phoneno, email, city, state)
    cursor.execute(sql)
    try:
        db.commit()
        return "Values Inserted"
    except:
        db.rollback()
        return "Error in Insertion"
    

def delete(phoneno):
    print phoneno
    #cursor.execute("set autocommit = 1")
     
    sql1= "select * from phonebook where phoneno = '%s'" % phoneno
    cursor.execute(sql1)
    result = cursor.fetchall()
    if not result:
        return "Record does not exist"
    #results = cursor.fetchall()
   
    sql2 = "delete from phonebook where phoneno = '%s'" % phoneno
    cursor.execute(sql2)
    try:
        db.commit()
    except:
        db.rollback()
    return "record deleted"

def search(email):
    list_data = []
    sql = "select * from phonebook where email = '%s'" % email;
    try:
        cursor.execute(sql)
        results=cursor.fetchall()
        if not results:
            return "Doesnt Exists"
        '''
        #val = results
        a = results.find("'")
        a = results.find("'",a+1)
        a = results.find("'",a+1)
        a = results.find("'",a+1)
        a = results.find("'",a+1)
        b = results.find("'",a+1)
        return "the phone no is %d " % val[a+1:b]
        '''
        for row in results:
            phone = row[2]
            return "the phone no is %s " % phone
        return results
    except:
        print "unable to excess"
            
    


server = SimpleXMLRPCServer(("localhost", 8000))
print "Listening on port 8000..."
server.register_function(search, "search")
server.register_function(delete, "delete")
server.register_function(insert,"insert")
server.serve_forever()
