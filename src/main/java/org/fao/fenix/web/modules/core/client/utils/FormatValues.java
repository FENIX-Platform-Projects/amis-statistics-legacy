package org.fao.fenix.web.modules.core.client.utils;


public class FormatValues {

	public static String formatValue(Double valueNumber, int maxNumberAfterComma) {
		maxNumberAfterComma++;
		String value = Double.toString(valueNumber);
//		System.out.println("value: " + value);
		if ( value.contains(".")) {
//			System.out.println("value lenght: " + value.length());
//			System.out.println("value idx: " + (value.indexOf(".") + maxNumberAfterComma));
			if ( value.length() < (value.indexOf(".") + maxNumberAfterComma)  ) {
//				System.out.println("value < : " + value);
				return value;
			}
			else {
				value = value.substring(0, value.indexOf(".") + maxNumberAfterComma);
//				System.out.println("value >: " + value);
				return value;
			}
			
		}
		else if (value.contains(",") ) {
			if ( value.length() < (value.indexOf(",") + maxNumberAfterComma)  ) {
//				System.out.println("value < : " + value);
				return value;
			}
			else {
				value = value.substring(0, value.indexOf(",") + maxNumberAfterComma);
//				System.out.println("value >: " + value);
				return value;
			}
		}
		else 
			return value;
		
	}
	
	
	public static String formatValue(String value, int maxNumberAfterComma) {
		maxNumberAfterComma++;
//		System.out.println("value: " + value);
		if ( value.contains(".")) {
//			System.out.println("value lenght: " + value.length());
//			System.out.println("value idx: " + (value.indexOf(".") + maxNumberAfterComma));
			if ( value.length() < (value.indexOf(".") + maxNumberAfterComma)  ) {
//				System.out.println("value < : " + value);
				return value;
			}
			else {
				value = value.substring(0, value.indexOf(".") + maxNumberAfterComma);
//				System.out.println("value >: " + value);
				return value;
			}
			
		}
		else if (value.contains(",") ) {
			if ( value.length() < (value.indexOf(",") + maxNumberAfterComma)  ) {
//				System.out.println("value < : " + value);
				return value;
			}
			else {
				value = value.substring(0, value.indexOf(",") + maxNumberAfterComma);
//				System.out.println("value >: " + value);
				return value;
			}
		}
		else 
			return value;
		
	}
	


}
