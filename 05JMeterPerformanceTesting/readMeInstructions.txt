Open cmd in your Jmeter /bin folder.
1.Create a html report from test plan (.jmx file):
jmeter -n -t "your .jmx file path" -l "where your .csv file want to be created" 
-e -o "where your html report want to be created"
2.Create a html report from a result file:
jmeter -g "location of your result .csv file" -o "where your html report want to be created"
