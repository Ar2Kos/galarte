import xlrd
import MySQLdb

# Open the workbook and define the worksheet
book = xlrd.open_workbook("updated new db.xlsx", encoding_override ='utf-8')
sheet_names = book.sheet_names()
# Establish a MySQL connection
database = MySQLdb.connect (host="localhost", user = "root", passwd = "ip20", db = "ip")
print("connected to the DB")

ART_PIECE_name = []
name_of_location = ""

# Get the cursor, which is used to traverse the database, line by line
cursor = database.cursor()

for i in range (0,len(sheet_names)):
	sheet = book.sheet_by_name(sheet_names[i])
	
	

	# Create the INSERT INTO sql query
	if sheet_names[i] == "STYLE":
		query = """INSERT INTO STYLE (name, era, description) VALUES (%s, %s, %s)"""
	elif sheet_names[i] == "AUTHOR":
		query = """INSERT INTO AUTHOR (name, style, born,death,description) VALUES (%s, %s, %s,%s,%s)"""
	elif sheet_names[i] == "ART_PIECE":
		query = """INSERT INTO ART_PIECE (name, author,style,date, description) VALUES (%s, %s, %s,%s,%s)"""
	elif sheet_names[i] == "PLACE":
		query = """INSERT INTO PLACE (name, price,country,city) VALUES (%s, %s, %s,%s)"""

	# Create a For loop to iterate through each row in the XLS file, starting at row 2 to skip the headers
	for r in range(1, sheet.nrows):
		if sheet.cell(r,0).value != "":   #check if the first cell is empty
			name = sheet.cell(r,0).value.strip()
			if sheet_names[i] == "STYLE":
				cursor.execute("""SELECT name FROM STYLE WHERE name = '%s' """ % (name.strip()))
				check =  cursor.fetchall()
				if(len(check) > 0):
					if(name == check[0][0]):
						break
				era	            = int(sheet.cell(r,1).value)
				description	    = sheet.cell(r,2).value
				
				values = (name, era, description)

			elif sheet_names[i] == "AUTHOR":
				cursor.execute("""SELECT name FROM AUTHOR WHERE name = '%s' """ % (name.strip()))
				check =  cursor.fetchall()
				if(len(check) > 0):
					if(name == check[0][0]):
						break

				style 			= sheet.cell(r,1).value
				cursor.execute("""SELECT ID FROM STYLE WHERE name = '%s' """ % (style.strip()))
				style_id =  cursor.fetchall()
				

				born 			= int(sheet.cell(r,2).value)
				death 			= int(sheet.cell(r,3).value)
				description 	= sheet.cell(r,4).value

				values = (name, style_id[0][0],born,death, description)

			elif sheet_names[i] == "ART_PIECE":
				ART_PIECE_name.append(name)
				author 			= sheet.cell(r,1).value
				cursor.execute("SELECT ID FROM AUTHOR WHERE name='%s' """ % (author.strip()))
				author_id =  cursor.fetchall()

				style 			= sheet.cell(r,2).value
				cursor.execute("SELECT ID FROM STYLE WHERE name='%s' """ % (style.strip()))
				style_id =  cursor.fetchall()

				date 			= int(sheet.cell(r,3).value)
				description 	= sheet.cell(r,4).value

				values = (name, author_id[0][0],style_id[0][0],date, description)			

			elif sheet_names[i] == "PLACE":
				name_of_location = name
				cursor.execute("""SELECT name FROM PLACE WHERE name = '%s' """ % (name.strip()))
				check =  cursor.fetchall()
				if(len(check) > 0):
					if(name == check[0][0]):
						break

				price 			= int(sheet.cell(r,1).value)
				country 		= sheet.cell(r,2).value
				city 			= sheet.cell(r,3).value
				
				values = (name,price,country,city)	

			# Execute sql Query
			print(values)
			cursor.execute(query, values)

	# Commit the transaction
	database.commit()

query = """INSERT INTO LOCATION (PLACE_ID, AUTHOR_ID,Piece) VALUES (%s, %s, %s)"""
for i in ART_PIECE_name:
	cursor.execute("""SELECT ID FROM PLACE WHERE name = '%s' """ % (name_of_location.strip()))
	PLACE_ID =  cursor.fetchall()
	cursor.execute("""SELECT ID,author FROM ART_PIECE WHERE name = '%s' """ % (i.strip()))
	Piece =  cursor.fetchall()


	values = (PLACE_ID[0][0],Piece[0][1],Piece[0][0])
	print(values)
	cursor.execute(query, values)

	database.commit()

# Close the cursor
cursor.close()

# Close the database connection
database.close()

# Print results
print("done")