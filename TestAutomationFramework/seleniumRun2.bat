call mvn surefire-report:report
call mvn site -DgenerateReports=false
call another_batch_file.bat
PAUSE