import xmlrpclib,sys


proxy = xmlrpclib.ServerProxy("http://localhost:8000/")

try:
    email_id = raw_input("Enter the Email id of the person to be searched        ")    
    print "result %s" % str(proxy.search(email_id))
except:
    print sys.exc_info()



try:
    phone_no = raw_input("Enter the phone no to be deleted.....        ")
    print "result is: %s" % str(proxy.delete(phone_no))
except:
    pass

try:
    inp = raw_input("Enter the details of the insert in comma separated values...")
    val = inp.split(',')
    #print "result %s" % str(proxy.insert('ram','mohan','919745689758','ram@mohan','indore','MP'))
    print "result %s" % str(proxy.insert(val[0],val[1],val[2],val[3],val[4],val[5]))
except:
    print sys.exc_info()


