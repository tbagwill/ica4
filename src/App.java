import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.text.DecimalFormat;

public class App {

    // Untyped output matrix
    public static Vector < Vector > C = new Vector < Vector > ();
    // Const matrix size = size^2
    public static final Integer size = 2;
    // Round to 2 decimal places
    public static DecimalFormat df = new DecimalFormat("#.###");

    public static void main(String[] args) throws Exception {
        // INIT STRING MATRICES
        Vector < Vector < String > > strMat1 = new Vector < Vector < String > > ();
        populateMatrix( strMat1, "string", size);
        previewMatrix(strMat1, "string");

        Vector < Vector < String > > strMat2 = new Vector < Vector < String > > ();
        populateMatrix( strMat2, "str", size);
        previewMatrix(strMat2, "string");

        // INIT INTEGER MATRICES
        Vector < Vector < String > > intMat1 = new Vector < Vector < String > > ();
        populateMatrix( intMat1, "integer", size);
        // previewMatrix(intMat1, "integer");

        Vector < Vector < String > > intMat2 = new Vector < Vector < String > > ();
        populateMatrix( intMat2, "int", size);
        // previewMatrix(intMat1, "integer");

        // INIT DECIMAL MATRICES
        Vector < Vector < String > > decMat1 = new Vector < Vector < String > > ();
        populateMatrix( decMat1, "decimal", size);
        // previewMatrix(decMat1, "decimal");
        // System.out.println( decMat1.toString() );

        Vector < Vector < String > > decMat2 = new Vector < Vector < String > > ();
        populateMatrix( decMat2, "dec", size);
        // previewMatrix(decMat2, "decimal");
        // System.out.println( decMat2.toString() );

        //*/
        // MATRIX FUNCTIONS PER TYPE

        // STRING
        // RULESET:
        // add: concatenate | mult: intersection | asterate: scramble
        // matrixAdd(strMat1, strMat2, "str");
        matrixMult(strMat1, strMat2, "str");
        // matrixAsterate(strMat1, "str");

        // INTEGER
        // RULESET:
        // add: add | mult: mult | asterate: replicate 3 times
        // matrixAdd(intMat1, intMat2, "int");
        // matrixMult(intMat1, intMat2, "int");
        // matrixAsterate(intMat1, "int");

        // DECIMAL
        // add: subtract | mult: divide | asterate: replicate 3 times about the decimal
        // matrixAdd(decMat1, decMat2, "dec");
        // matrixMult(decMat1, decMat2, "dec");
        // matrixAsterate(decMat1, "dec");
        printMatrix( C );

    }

    public static void matrixAdd( Vector < Vector < String > > vect1, Vector < Vector < String > > vect2, String type ){

        // Check dimensions
        if( vect1.size() != vect2.elementAt(0).size() ){
            throw new IllegalArgumentException();
        }

        // Check type for `string`
        if( type.equalsIgnoreCase("string" ) || type.equalsIgnoreCase("str" ) ){
            // Base Rule: Concat strings
            for( int i = 0; i < vect1.size(); i++ ){
                // Add second dimension
                C.add( new Vector ());
                for( int j = 0; j < vect1.elementAt(i).size(); j++){
                    String str1 = vect1.elementAt(i).elementAt(j);
                    String str2 = vect2.elementAt(i).elementAt(j);
                    ( ( Vector < String > ) C.elementAt(i) ).add( str1.concat(str2) );
                }
            }
            // Check type for `integer`
        } else if ( type.equalsIgnoreCase( "integer" ) || type.equalsIgnoreCase( "int" ) ){
            // Base Rule: Integer Addition
            for( int i = 0; i < vect1.size(); i++ ){
                C.add( new Vector ());
                for( int j = 0; j < vect1.elementAt(i).size(); j++){
                    Integer int1 = Integer.parseInt(vect1.elementAt(i).elementAt(j));
                    Integer int2 = Integer.parseInt(vect2.elementAt(i).elementAt(j));
                    ( ( Vector < Integer > ) C.elementAt(i) ).add( int1 + int2 );
                }
            }
            // Check type for `decimal`
        } else if ( type.equalsIgnoreCase( "decimal") || type.equalsIgnoreCase( "dec") ){
            // Base Rule: Float Addition
            for( int i = 0; i < vect1.size(); i++ ){
                C.add( new Vector ());
                for( int j = 0; j < vect1.elementAt(i).size(); j++){
                    Double dec1 = Double.parseDouble(vect1.elementAt(i).elementAt(j));
                    Double dec2 = Double.parseDouble(vect2.elementAt(i).elementAt(j));
                    ( ( Vector < Double > ) C.elementAt(i) ).add( dec1 - dec2 );
                }
            }
        } else {
            System.out.println( "Invalid matrix type." );
        }
    }

    public static void matrixMult( Vector < Vector < String > > vect1, Vector < Vector < String > > vect2, String type ){
        // Check dimensions
        if( vect1.size() != vect2.elementAt(0).size() ){
            throw new IllegalArgumentException();
        }

        // Check type for `string`
        if( type.equalsIgnoreCase( "string" ) || type.equalsIgnoreCase( "str" ) ){
            // Base Rule: Dot Product Combination

            // init temp strings
            String str1 = "", str2 = "", str3 = "", str4 = "";

            // C[i][j] += ( vect1[k][j] + vect2[i][k] )
            C.add( new Vector ());
            C.add( new Vector ());
            for( int i = 0; i < vect1.size(); i++ ){
                // Add second dimension
                for( int j = 0; j < vect1.elementAt(i).size(); j++){
                    str1 = vect1.elementAt(j).elementAt(0);
                    str2 = vect2.elementAt(0).elementAt(i);
                    for(int k = 0; k < str1.length(); k++) {
                        char temp = str1.charAt(k);
                        if (str2.indexOf(temp) != -1 && str3.indexOf(temp) == -1) {
                            str3 = str3 + temp;
                        }
                    }

                    str1 = vect1.elementAt(j).elementAt(1);
                    str2 = vect2.elementAt(1).elementAt(i);
                    for(int k = 0; k < str1.length(); k++) {
                        char temp = str1.charAt(k);
                        if(str2.indexOf(temp) != -1 && str4.indexOf(temp) == -1) {
                            str4 = str4 + temp;
                        }
                    }

                    C.elementAt(j).add( str3 + str4 );
                    str3 = "";
                    str4 = "";
                }
            }
            // Check type for `integer`
        } else if ( type.equalsIgnoreCase( "integer" ) || type.equalsIgnoreCase( "int" ) ) {
            // Base Rule: Dot Product Integer Multiplication

            // init temp int
            Integer res = 0, int1 = 0, int2 = 0;

            for( int i = 0; i < vect1.size(); i++ ){
                // Add second dimension
                C.add( new Vector ());
                for( int j = 0; j < vect1.elementAt(i).size(); j++){
                    for( int k = 0; k < vect1.elementAt(j).size(); k++){
                        int1 = Integer.parseInt( vect1.elementAt(k).elementAt(j) );
                        int2 = Integer.parseInt( vect2.elementAt(i).elementAt(k) );
                        res += int1 * int2;
                    }
                    C.elementAt(i).add( res );
                }
            }
            // Check type for `decimal`
        } else if ( type.equalsIgnoreCase( "decimal" ) || type.equalsIgnoreCase( "dec" ) ) {
            // Base Rule: Dot Product Double Multiplication

            // init temp double
            Double res = 0.0, dub1 = 0.0, dub2 = 0.0;

            for( int i = 0; i < vect1.size(); i++ ){
                // Add second dimension
                C.add( new Vector ());
                for( int j = 0; j < vect1.elementAt(i).size(); j++){
                    for( int k = 0; k < vect1.elementAt(j).size(); k++){
                        dub1 = Double.parseDouble( vect1.elementAt(k).elementAt(j) );
                        dub2 = Double.parseDouble( vect2.elementAt(i).elementAt(k) );
                        res = dub1 / dub2;
                    }
                    C.elementAt(i).add( df.format( res ) );
                }
            }

        } else {
            System.out.println( "Invalid matrix type." );
        }

    }

    public static void matrixAsterate( Vector < Vector < String > > vect, String type ){

        // Check type for `string`
        if( type.equalsIgnoreCase("string" ) || type.equalsIgnoreCase("str" ) ){
            // Base Rule: Replicate 3 times

            // init temp string
            String temp1 = "", temp2 = "";
            for( int i = 0; i < vect.size(); i++ ){
                C.add( new Vector() );
                for( int j = 0; j < vect.elementAt(i).size(); j++ ){
                    // TODO
                    temp1 = vect.elementAt(i).elementAt(j);

                    temp2 = randomizeString( temp1 );

                    C.elementAt(i).add( temp2 );
                }
            }
            // Check type for `integer`
        } else if( type.equalsIgnoreCase( "integer") || type.equalsIgnoreCase( "int" ) ){
            // Base Rule: Replicate 3 times

            // init temp string
            String temp = "";
            for( int i = 0; i < vect.size(); i++ ){
                C.add( new Vector() );
                for( int j = 0; j < vect.elementAt(i).size(); j++ ){
                    // TODO
                    temp = vect.elementAt(i).elementAt(j);
                    temp = temp.concat( temp.concat( temp ) );
                    C.elementAt(i).add( temp );
                }
            }
            // Check type for `decimal`
        } else if( type.equalsIgnoreCase( "decimal") || type.equalsIgnoreCase( "dec" ) ){
            // Base Rule: Replicate 3 times

            String temp = "";
            int index = 0;
            for( int i = 0; i < vect.size(); i++ ){
                C.add( new Vector() );
                for( int j = 0; j < vect.elementAt(i).size(); j++ ){
                    temp = vect.elementAt(i).elementAt(j);
                    char[] chars = temp.toCharArray();
                    for( int x = 0; x < temp.length(); x++ ){
                        if( chars[x] == '.' ){
                            index = x;
                        }
                    }
                    String sub1 = temp.substring( 0, index );
                    String sub2 = temp.substring( index + 1 );
                    C.elementAt(i).add( sub1.concat( sub1.concat( sub1.concat( "." + sub2.concat( sub2.concat( sub2 ) ) ) ) ) );
                }
            }
        } else {
            System.out.println( "Invalid matrix type." );
        }
    }

    public static void populateMatrix( Vector < Vector < String > > v, String type, Integer size ){
        // Import data file
        File file = new File(".\\lib\\data.txt");

        // Init respective arrays
        String[] strs = new String[10];
        String[] ints = new String[10];
        String[] decs = new String[10];

        // Scan doc data, populate arrays
        try {
            Scanner data = new Scanner(file);
            int x = 0;
            while( data.hasNextLine() ){
                if( x < 10){
                    strs[x] = data.nextLine();
                    x++;
                } else if ( x < 20 ) {
                    ints[x - 10] = data.nextLine();
                    x++;
                } else {
                    decs[x - 20] = data.nextLine();
                    x++;
                }
            }
            data.close();
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        }

        // Populate matrix with relevant data
        // Check type for `string`
        if( type.equalsIgnoreCase("string" ) || type.equalsIgnoreCase("str" ) ){
            for( int i = 0; i < size; i++ ){
                v.add( new Vector < String > () );
                for( int j = 0; j < size; j++ ){
                    v.elementAt(i).add( strs[ random() ] );
                }
            }
            // Check type for `integer`
        } else if( type.equalsIgnoreCase( "integer") || type.equalsIgnoreCase( "int" ) ){
            for( int i = 0; i < size; i++ ){
                v.add( new Vector < String > () );
                for( int j = 0; j < size; j++ ){
                    v.elementAt(i).add( ints[ random() ] );
                }
            }
            // Check type for `decimal`
        } else if( type.equalsIgnoreCase( "decimal") || type.equalsIgnoreCase( "dec" ) ){
            for( int i = 0; i < size; i++ ){
                v.add( new Vector < String > () );
                for( int j = 0; j < size; j++ ){
                    v.elementAt(i).add( decs[ random() ] );
                }
            }
        }
    }

    // Helper method to print untyped output matrix
    public static void printMatrix( Vector < Vector > v ){
        System.out.print( "\t Output: ");
        for( int i = 0; i < v.size(); i++ ){
            System.out.println();
            for( int j = 0; j < v.elementAt(i).size(); j++ ){
                System.out.print( "\t" + v.elementAt(i).elementAt(j) + "\t" );
            }
            System.out.println();
        }
    }

    public static void previewMatrix( Vector < Vector < String > > v, String type ){
        System.out.print( "\t Preview Matrix of Type " + type);
        for( int i = 0; i < v.size(); i++ ){
            System.out.println();
            for( int j = 0; j < v.elementAt(i).size(); j++ ){
                System.out.print( "\t" + v.elementAt(i).elementAt(j) + "\t" );
            }
            System.out.println();
        }
    }

    // RNG for assigning random data to matrices
    public static int random(){
        Random r = new Random( System.nanoTime() );
        int rand = r.nextInt( 10 );
        return rand;
    }

    public static String randomizeString( String s ){
        Vector < String > v = new Vector < String > ();
        for( int i = 0; i < s.length(); i++ ){
            v.add( s.substring( i, i + 1 ) );
        }

        for( int i = 0; i < s.length(); i++ ){
            Random r = new Random( System.nanoTime() );
            int x = r.nextInt( s.length() );
            int y = r.nextInt( s.length() );
            String str1 = v.elementAt(x);
            String str2 = v.elementAt(y);
            v.set( x, str2 );
            v.set( y, str1 );
        }

        String scramble = "";

        for( int i = 1; i < v.size(); i++ ){
            scramble = scramble.concat( v.elementAt(i) );
        }

        return scramble;
    }

}
