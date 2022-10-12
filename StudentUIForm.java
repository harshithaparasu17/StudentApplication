package maps2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class StudentUIForm {

	
	
	
	
	public static void main(String[] args) {
	
		
		StudentServerCode server = new StudentServerCode();
		
		Scanner sc = new Scanner(System.in);
		Scanner scStr = new Scanner(System.in);
		
		while(true)
		{
			System.out.println("\n\n\n        *** Application ***\n\n");
			System.out.println("1. Add Student ");
			System.out.println("2. Update Student Profile");
			System.out.println("3. Add Student Marks. ");
			System.out.println("4. View Student Marks by Roll Number & Stream. ");
			System.out.println("5. View Students Based on Stream. ");
			System.out.println("6. View Student Marks (High to low) Based on Stream.");
			System.out.println("7. View Student Based on State.");
			System.out.println("8. Get Student Profile Based on Roll Number & Stream ");
			System.out.println("9. View All Students"); // print entire map
			
			System.out.println("0. EXIT");
			System.out.println("\n\t Select Option Of Your Choice: ");
			
			switch (sc.nextInt()) {
			case 1:
				
				System.out.println("Enter Student Name ");
				String studentName = scStr.nextLine();
				
				System.out.println("Enter Student Stream ");
				System.out.println("IT/CS/ELE/MCA");
				String studentStream = scStr.nextLine();
				studentStream = studentStream.trim().toUpperCase();
				
				int rollNum = getRandom();
				
				Student basicStudent = new Student(rollNum, studentName, studentStream);
				
				int count = server.insertStudent(basicStudent);
				System.out.println("Random roll Number assigned to the Student is: "+rollNum);
				System.out.println(" Student Added in Stream "+studentStream+" total Students are :- "+count);
				break;

			case 2:
				
				System.out.println("Enter Student Roll Number ");
				int rollNumber = sc.nextInt();
				
				System.out.println("Enter Student Stream ");
				String searchStream = scStr.nextLine();
				
				Student s = server.getStudentBasedOnRollNumberAndStream(rollNumber, searchStream);
				
				if(s != null)
				{
					// if student is not null then only update the profile otherwise render message

					System.out.println("Enter House Number ");
					String houseNumber = scStr.nextLine();
					
					System.out.println("Enter City ");
					String city = scStr.nextLine();
					
					System.out.println("Enter State ");
					String state = scStr.nextLine();
					
					System.out.println("Enter Email ");
					String email = scStr.nextLine();
					
					System.out.println("Enter Phone Number ");
					long phoneNumber = sc.nextLong();
					
					StudentDetails profile = new StudentDetails(houseNumber, city, state, phoneNumber, email);
					
					Student updatedStudent = server.updateStudentProfile(s, profile);
					printStudents(updatedStudent);
				}
				else
				{
					System.out.println(" \n STUDENT NOT FOUND WRONG ROLL NUMBER OR STREAM \n");
					System.out.println("Entered Roll Number "+rollNumber);
					System.out.println("Entered Stream "+searchStream);
					
				}
				
				
				
				break;

			case 3:
				System.out.println("Enter the Student Stream");
				System.out.println("IT/CS/ELE/MCA");
				String searchstream = scStr.nextLine();
				searchstream = searchstream.trim().toUpperCase();
				System.out.println("Enter the student roll number: ");
				int rollno=sc.nextInt();
				Student desiredStudent=server.getStudentBasedOnRollNumberAndStream(rollno, searchstream);
				
				//entering the subject wise marks of desiredStudent
				
				Subject java=new Subject("OOPSJava", "abc",85);
				Subject st=new Subject("testing", "dsy",72);
				Subject db=new Subject("database", "abc",99);
				Subject os=new Subject("operatingststem", "abc",75);
				Subject cloud=new Subject("cloud", "abc",68);
				
				List<Subject> subjectList=new ArrayList<>();
				subjectList.add(java);
				subjectList.add(st);
				subjectList.add(db);
				subjectList.add(os);
				subjectList.add(cloud);
		
				
				//it takes student info and the student marks as list of students
				Student desiredStudentwithMarks=server.addStudentMarks(desiredStudent,subjectList);
				
				printStudents(desiredStudentwithMarks);
				
				
				break;

			case 4:
				server.getStudentBasedOnRollNumberAndStream(0, null);
				
				break;

			case 5:
				System.out.println("Enter Student Stream ");
				System.out.println("IT/CS/ELE/MCA");
				String searchedStream = scStr.nextLine();
				searchedStream = searchedStream.trim().toUpperCase();
				
				List<Student> allStudents =  server.getStudentByStream(searchedStream);
				for (Student student : allStudents) {
					printStudents(student);
				}
				
				break;

			case 6:
				server.getStudentByStreamSortedByMarks(null);
				break;

			case 7:
				
				break;
				
			case 8:
				
				break;
				
			case 9:
				
				break;
				
				
			case 0:				
			default:
				System.exit(0);
			}//end switch
			
		}//end while
		
	}//end of Main

	
	public static void printStudents(Student student)
	{
		
			
			System.out.print("\n"+student.getRollNum()+"\t");
			System.out.print(student.getName()+"\t");
			System.out.print(student.getStream()+"\t");
			
			System.out.println("\n Student Profile \n");
			StudentDetails profile = student.getProfile();
			if(profile != null)
			{
				System.out.print(profile.getHouseNumber()+" "+profile.getCity()+" "+profile.getState()+" \t");
				System.out.print(profile.getPhoneNumber()+" \t");
				System.out.print(profile.getEmail()+" \t");
			}
			else
			{
				System.out.println(" *** Profile Not Update !!!");
			}
			
			List<Subject> subjects = student.getAllSubjects();
			
			System.out.println("\n  Subject Details \n");
			
			if(subjects != null)
			{
				for (Subject subject : subjects) {
					System.out.println(subject.getSubjectName()+" - "+subject.getMarksObtain());
				}
			}
			else
			{
				System.out.println("  Marks Not Updated By Faculty !!! ");
			}
			
			System.out.println("\n____________________________________________________________________________\n");
			
		
	}
	
	public static int getRandom()
	{
		return new Random().nextInt(1000);
	}

	
}