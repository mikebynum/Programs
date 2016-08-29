#!/usr/bin/perl -w

# Name		:Mike Bynum
# Class		:CSCI 2850-01


use strict;
use constant false => 0;
use constant true  => 1;



#make a regex that goes and finds all of the white space and make it so that there is only one space
#split on semicolons to get statements and then split on white space to get all the tokens
#look for labels (hash table)and throw away all the comments
#use a hash table to split on colons

#First, lets read in a file

my $prog_name; #variable to store the program name in

my @functions; #array to hold the functions
my @program_tokens;
my @statement;

my @temp_var;
my $num_temp_variables;

my $good_line = "";

my $i;

my $firstWord;
my $action;
my %variables;#hash table to store the variables in
my %function_locations;
my %labeled_statement_loc;

my $main_location;

## DEFINE THE NOTATION HERE!
my $notation; 



#********************************POSTFIX STUFF******************************************

## Position hash with anony array
my %pos_hash = ("!" =>0, "+" =>1, "-" =>2, "*" =>3, "/" =>4, "(" =>5, ")" => 6);
my @postfix;
#infix data
my %action_hash = ("!" => [4, 1, 1, 1, 1, 1, 5],
                            "+" => [2, 2, 2, 1, 1, 1, 2],
                            "-" => [2, 2, 2, 1, 1, 1, 2],
                            "*" => [2, 2, 2, 2, 2, 1, 2],
                            "/" => [2, 2, 2, 2, 2, 1, 2],
                            "(" => [5, 1, 1, 1, 1, 1, 3]
                           );
#holders
my @texas;
my @cali;
$texas[0] = "!";
my $current_pos = 0;
#************************************************************************************


if (!$ARGV[0])
{
	print "Need a file name!";
	exit 55;
}
else
{
	$prog_name = $ARGV[0];
}

open(IN, $prog_name) or die "Could not open file\n";
my @prog_data = <IN>;

close IN;

foreach my $line (@prog_data)
{
	$line =~ s/\/\/.*//g; #remove the comments
	
	#need to make line end in a semicolon so we can split it later...
	
	$line =~ s/([A-Z])/lc($1)/eg; #make everything lowercase
	
	$line =~ s/\+/ \+ /g;
	$line =~ s/-/ - /g;
	$line =~ s/\*/ \* /g;
	$line =~ s/\// \/ /g;
	$line =~ s/%/ % /g;
	$line =~ s/=/ = /g;
	$line =~ s/;/ ; /g;
	$line =~ s/:/ : /g;
	$line =~ s/,/ , /g;
	$line =~ s/"/ " /g;
	$line =~ s/\(/ \( /g;
	$line =~ s/\)/ \) /g;
	$line =~ s/^\s+//g;
	
	$line =~ s/\n//g; #remove newlines
	
	$line =~ s/\s+/ /g; #removes unnecessary white space
	#$line =~ s/\s\;\s(\S+)/ ; \n$1 /g;
	
	#print "$line\n\n";
	#$line =~ s/\s$|_$//g; #remove newlines at the end
	#print "$line\n";
	
	
	
	if ($line =~  /.+\s;\s.+/)
	{
		#print"Need to split the semicolons\n";
		$good_line = $good_line . $line;
		push(@program_tokens, split(/(?<=;) /, $good_line));
		$good_line = "";
	}
	elsif($line =~ /^\S+ :.*/)
	{
		#print"Need to split the label\n";
		$good_line = $good_line . $line;
		push(@program_tokens, split(/(^\S+ :) /,$good_line));
		$good_line = "";
	}
	elsif($line =~ /.+;/)
	{
		#print"just push the line on\n";
		$good_line = $good_line . $line;
		push(@program_tokens, $good_line);
		$good_line = "";
	}
	elsif($line =~ /func/)
	{
		#print"This is a funciton\n";
		$good_line = $good_line . $line;
		push(@program_tokens, $good_line);
		$good_line = "";
	}
	elsif($line =~ /.+/)
	{
		#print"good line is: $good_line & line is: $line\n";
		$good_line = $good_line . $line;
		#print"We need to do something else...The line is $good_line\n";
	}
	
	#?<=
	#(?=;) 
}

#print"@program_tokens";
my $num_function = 0;
my $token_number = 0;

foreach my $token (@program_tokens)
{
	if($token =~ /endfunc.*/gi)
	{
		
		$functions[$num_function][$token_number] = $token;
		$num_function++;
		$token_number = 0;
		#print"We have reached the end\n";
		
	}
	else
	{
		$functions[$num_function][$token_number] = $token;
		$token_number++;
	}
}

my $count;
#print "Print Using ForEach\n";
foreach my $row(@functions)
{
   $count = 0;
   foreach my $val(@$row)
   {
   	#print "$count\n";
		#print $val;print "\n";
      if ($val =~ /^(\S+) :/)
      {
      	#print "I found a label at row $count\n";
      	$variables{$1} = $count;
      }
      elsif ($val eq "")
      {
      	#print"Now deleting empty row\n";
      	delete $functions[$count];
      }
      $count++;
   }
   #print "\n\n";
}

#print "there are $num_function functions.\n";



for (my $x=0; $x<$num_function; $x++)
{
	
		$functions[$x][0] =~ /func (\S+)/;
		#print "Main was not found at $x ($functions[$x][1])\n";
		#print "A function called $1 was found at location $x\n";
		$function_locations{$1} = $x; 
	
}

#print "Lets start at main now....it consists of: ";()
#exit();
executeFunction('main');

#my $length_of_func=0;
#
#$length_of_func = $#{$functions[$function_locations{'main'}]} + 1;
#
##print "$length_of_func lines\n";
#
##exit;
#
#for ($i = 0; $i < $length_of_func; $i++)
#{
#	print "\nThis line $i of main is $functions[$function_locations{'main'}][$i]\n";
#
#	my $isEmpty;
#	$isEmpty = parseStatement($functions[$function_locations{'main'}][$i]);
#	if ($isEmpty)
#	{
#		#print"On to the next one\n";
#		next;
#	}
#	else
#	{
#		findAction();
#		doAction($functions[$function_locations{'main'}][$i]);
#	}
#		
#}


sub parseStatement
{
	my $isEmpty = false;
	
	if ($_[0] eq "")
	{
		#print "This line is empty\n";
		$isEmpty = true;
		return $isEmpty;
	}
	else
	{
		@statement = split(/_/, $_[0]);
	
#		foreach my $word (@statement)
#		{
#			#print $word; print"\n";
#		}
		$firstWord = $statement[0];
		#print "This statement starts with $firstWord\n";
		return $isEmpty;
	}
	
	
}



sub findAction
{
	if ($firstWord =~ m/^func.*/i)#beginning of function
	{
		$action = 0;
	}

	elsif ($firstWord =~ m/write/i)#write something
	{
		$action = 1;
	}
	elsif ($firstWord =~ m/read/i)#read input
	{
		$action = 2;
	}
	elsif ($firstWord =~ m/\S+\s=\s\S+\s\(.*/)#function assignment
	{
		$action = 3;
	}
	elsif ($firstWord =~ m/\S+\s=\s.*/i)#expression
	{
		$action = 4;
	}
	elsif ($firstWord =~ m/\S\s:.*/i)#labeled statement
	{
		$action =5;
	}
	elsif ($firstWord =~ m/if \(.+/)#IF statement
	{
		$action = 6;
	}
	elsif ($firstWord =~ /^endfunc/)#end of function
	{
		$action = 7;
	}
	else
	{
		$action = 99;
	}
}

sub doAction
{
	if ($action == 0)
	{
		$_[0] =~ /func (\S+)\s\(\s*(.*)\s\)/;
		#print"Beginning of a function\n";
		$variables{$1} = 0;
		
		push(@temp_var, split(' , ', $2));
		$num_temp_variables = @temp_var;
		
		#print "There are $num_temp_variables variables passed.  The information passed is @temp_var\n";
		
		#print "The hash table is \n\n";
#		my @relationships = keys %variables;
#		foreach my $who ( @relationships )
#		{
#			#printf "%15s is %s\n", $who, $variables{$who};
#		}
		return (" ");
	}
	elsif ($action == 1)#PRINT funtion
	{
		#print"We are going to do a print statement\n";
		if ($_[0] =~ /.*\(.*"\s(.*)\s".*/g)#print strings
		{
			print "$1\n";
		}
		elsif ($_[0] =~ /write\s\(\s(\S+)\s\)/i)#print variables
		{
			#print "Need to find the value of $1\n";
			#print "That value is $variables{$1}\n";
			print "$variables{$1}\n";
		}	
		return (" ");	
	}
	elsif ($action == 2)#READ function
	{
		#print"We are going to do a read statement\n";
		#print "the function is $_[0]\n";
		$_[0] =~ /\S+\s\(\s(.*)\s\).*/;
		#print "The identifier is $1\n";
		#do read action here
		my $message;
		chomp($message = <STDIN>);
		#print "The value of $1 is $message\n";
		#need to put this in the hash of %variables
		$variables{$1} = $message;
		
		#print "The hash table is \n\n";
#		my @relationships = keys %variables;
#		foreach my $who ( @relationships )
#		{
#			#printf "%15s is %s\n", $who, $variables{$who};
#		}
		return (" ");
	}
	elsif ($action == 3)#function ASSIGNMENT
	{
		$_[0] =~ /(\S)+\s=\s(\S+)\s\(\s*(.*)\s\)/;
		my $answer = $1;
		#print"We are going to do a function and store the results in $answer\n";
		
		#print"The function we are looking for is $2\n";
		#print "Found the function at function array $function_locations{$2}\n";
		#print "going to pass parameters of $3\n";
		my $result = executeFunction($2, $3);
		#print "The Result it $result\n\n\n\n\n";
		$variables{$answer} = $result;
		
		#print "The hash table is \n\n";
#		my @relationships = keys %variables;
#		foreach my $who ( @relationships )
#		{
#		#	printf "%15s is %s\n", $who, $variables{$who};
#		}
		return (" ");
	}
	elsif ($action == 4)#EXPRESSION
	{
		#print"This is an expression\n";
		#print"The expression is $_[0]\n";
		if($_[0] =~ /(\S+)\s=\s(\d+)/)
		{
		#	print"This is an assignment\n";
			#print"The identifier is $1\n";
			#print"The value is $2\n";
			$variables{$1} = $2;
			
		#	print "The hash table is \n";
#			my @relationships = keys %variables;
#			foreach my $who ( @relationships )
#			{
#		#		printf "%15s is %s\n", $who, $variables{$who};
#			}
		}
		elsif($_[0] =~ /(\S+)\s=\s(\S+)\s;/)
		{
		#	print"This is variable assignment\n";
			#print"The identifier is $1\n";
			#print"The value is $2\n";
			$variables{$1} = $variables{$2};
			
		#	print "The hash table is \n";
#			my @relationships = keys %variables;
#			foreach my $who ( @relationships )
#			{
#		#		printf "%15s is %s\n", $who, $variables{$who};
#			}
		}
		elsif($_[0] =~ /(\S+)\s=\s(.*)/)
		{
			my $current_value = $variables{$1};
		#	print"The identifier is $1\n";
		#	print"Its current value is $current_value\n";
		#	print"The value is $2\n";
			$variables{$1} = $2;
			
		#	print "The hash table is \n\n";
			my @relationships = keys %variables;
#			foreach my $who ( @relationships )
#			{
##				printf "%15s is %s\n", $who, $variables{$who};
#			}
			
#			print "Need to do some math now\n";
			doMath($2,$1,$current_value);
		}
		return (" ");
	}
	elsif ($action == 5)#labeled statement
	{
		#my $result;
#		print "This is a labeled statement\n";
#		print "The statement is $_[0]\n";
		if($_[0] =~ /(\S+)\s:\s(.*)\sto\s(\S+)/)
		{
#			print "The label is $1\n";
#			print "The statement is $2\n";
#			print "It is going to $3\n";
		}
		elsif($_[0] =~ /(\S+)\s:/)
		{
#			print"There is only a label here.  \n";
			
		}
		return $1;
	}
	elsif ($action == 6)#IF statement
	{
		my $ifIStrue = false;
		my $labelToGoTo;
#		print"This is an IF statement.  It is $_[0]\n";
		
		if ($_[0] =~/if \( true \) to (\S+)/)
		{
#			print"This is a true statement\n";
			$ifIStrue = true;
			$labelToGoTo = $1;
		}
		else
		{
			$_[0] =~ /if \( (\S+) \( (.+) \) \) to (\S+) ;/;
			#print"The IF statement tests for $1 between $2 and goes to $3\n";
			my @testvars = split / , /,$2;
			my $testVar1 = $variables{$testvars[0]};
			my $testVar2 = $variables{$testvars[1]};
			
#			print "var1 is $testVar1 and var2 is $testVar2\n";
			$labelToGoTo = $3;
			
			if($1 eq "eq")
			{
				#print "this is an equals\n";
				if ($testVar1 == $testVar2)
				{
#					print "$testVar1 equals $testVar2\n";
					$ifIStrue = true;
				}
				
			}
			elsif($1 eq "ne")
			{
				if ($testVar1 != $testVar2)
				{
#					print"$testVar1 does not equals $testVar2\n";
					$ifIStrue = true;
				}
			}
			elsif($1 eq "lt")
			{
				if ($testVar1 < $testVar2)
				{
#					print"$testVar1 less than $testVar2\n";
					$ifIStrue = true;
				}
			}
			elsif($1 eq "le")
			{
				if ($testVar1 <= $testVar2)
				{
#					print"$testVar1 less than or equals $testVar2\n";
					$ifIStrue = true;
				}
			}
			elsif($1 eq "ge")
			{
				if ($testVar1 >= $testVar2)
				{
#					print"$testVar1 greater than or equals $testVar2\n";
					$ifIStrue = true;
				}
			}
			elsif($1 eq "gt")
			{
				if ($testVar1 > $testVar2)
				{
#					print"$testVar1 is greater than $testVar2\n";
					$ifIStrue = true;
				}
			}
			else
			{
				print"OOPS!\n";
			}
		}
		
		if ($ifIStrue)
		{
			my $lineTo = $variables{$labelToGoTo};
#			print "We need to go to line after $labelToGoTo located on line $lineTo\n";
			
			return ("line $lineTo");
			#exit;
		}
		else
		{
			return (" ");
		}
		
	}
	elsif ($action == 7)
	{
#		print "This is the end of the function\n";
		return (" ");
	}
	else
	{
		print"somthing is WRONG!!\n";
		return (" ");
	}
}

sub executeFunction
{
	my @passedVariables;
	#print "Going to execute $_[0]\n";
	my $length_of_subfunc = $#{$functions[$function_locations{$_[0]}]} + 1;
	#print "Lets start at $_[0] now....it consists of: ";
	#print "$length_of_subfunc lines\n";
	if($_[1]){push(@passedVariables, split(' , ', $_[1]));}
	
	my $num_passed_variables = @passedVariables;
	
	#print "There are $num_passed_variables variables passed.  The information passed is @passedVariables\n";
	#print "There are $num_temp_variables variables passed.  The information passed is @temp_var\n";
	
	my $loopStart = 0;
	
	for (my $j = $loopStart; $j < $length_of_subfunc; $j++)
	{
		my $isEmpty;
#		print "Current line is $j\n";
		
		#print "\nThis line of $_[0] is $functions[$function_locations{$_[0]}][$j]\n";
		$isEmpty = parseStatement($functions[$function_locations{$_[0]}][$j]);
		if ($isEmpty)
		{
			#print"On to the next one\n";
			next;
		}
		else
		{
			my $result = "test";
			findAction();
			$result = doAction($functions[$function_locations{$_[0]}][$j]);
			if($result =~ /line (\d+)/)
			{
#				print "Change the current line to $1\n";
				$j = $1;
				#next;
			}
			elsif ($action == 0)
			{
#				print "There are $num_passed_variables variables passed.  The information passed is @passedVariables\n";
				#print "There are $num_temp_variables variables passed.  The information passed is @temp_var\n";
				my $temp_val;
				for (my $xx = 0; $xx < $num_passed_variables; $xx++)
				{
					$temp_val = $variables{$passedVariables[$xx]};
					$variables{$temp_var[$xx]} = $temp_val;
				}
			}
			elsif ($action == 5)
			{
#				print"The label is $result and located on line $j\n";
				#$variables{$result} = $j;
			}
		}
		
		#print "The hash table is \n\n";
#		my @relationships = keys %variables;
#		foreach my $who ( @relationships )
#		{
#			#printf "%15s is %s\n", $who, $variables{$who};
#		}
			
		
	}
	return $variables{$_[0]};
}

sub doMath
{
#	print "Lets do some math on $_[0] and assign it to $_[1] that has a current value of $_[2]\n";
	
	$notation = $_[0];
	my $leftValue = $_[2];
	my $leftVar = $_[1];
	my $replace;
	
	while ($notation =~ /\s*([a-z]+)\s/g)
	{
		$replace = $1;
#		print "There is a variable to be taken out, it is $replace\n";
		
		if ($leftVar eq $replace)
		{
#			print"There is a variable in left $leftVar that is the same as the right $replace\n";
			$notation =~ s/$replace/$_[2]/;
		}
		else
		{
			$notation =~ s/$replace/$variables{$replace}/;
		}
	
	}
	
	#my @replace_var = $replace =~ m/\s([a-z]+)\s/g;
	
	#print "The variables to replace are @replace_var\n";
	
	#$notation =~ /\s([a-z]+)\s/g;
	
	#$notation = "( 41.5 + ( 81 / 41.5 ) ) / 2";
	$notation = "($notation)"; #add parenthesis for parsing
#	print "my expression is $notation\n";
	
	my $expr_result = doPostfix();
	
#	print"Now assign into $_[1]\n";
	$variables{$_[1]} = $expr_result;
	
#	print "The hash table is \n";
#	my @relationships = keys %variables;
#	foreach my $who ( @relationships )
#	{
##		printf "%15s is %s\n", $who, $variables{$who};
#	}

	#my @results = &postFix($expression);
	
	#print "The result is $expr_result\n";
	#exit;
}



#*******************************POSTFIX*****************************************************

#Define functions
sub seek_action 
{
    my $cur_texas = shift;
    $cur_texas = $texas[$cur_texas];
    my $symbol = shift;
    my $move_to = $pos_hash{$symbol};
    return $action_hash{"$cur_texas"}->[$move_to];
}

sub postfix_cal 
{
    my $a;
    my $b;
    my @postfix1 = @_;
    my @ret_stack;
    my $result;
    #print "Postfix is @postfix1\n";
    foreach my $o (@postfix1) {
        if ($o =~ /\d+/) {
            push (@ret_stack, $o);
        }
        else {
            $b = pop (@ret_stack);
            $a = pop (@ret_stack);
            if ($o eq '+') {
                $result = $a + $b;
            }
            elsif ($o eq '-') {
                $result = $a - $b;
            }
            elsif ($o eq '*') {
                $result = $a * $b;
            }
            elsif ($o eq '/') {
                $result = $a / $b;
            }
            push (@ret_stack, $result);
        }
    }
    return pop(@ret_stack);
}

sub doPostfix
{
	while ($notation =~ /(\d+\.*\d*|\/|\*|\-|\+|\(|\))/g) 
	{
    	push (@postfix, $1);
	}
	
	my $i;
	for ($i = 0; $i < scalar@postfix; $i++) 
	{
	    my $actions;
	    #find action
	    if ($postfix[$i] !~ /\d+/) 
	    {
	        $actions = seek_action($current_pos, $postfix[$i]);
	    }
	    else 
	    {
	        push(@cali, "$postfix[$i]");
	        next;
	    }
	    if (defined($actions)) {
	        #perform action
	        if ($actions == 1) {
	            push (@texas, $postfix[$i]);
	            $current_pos = scalar@texas;
	            $current_pos--;
	            next;
	        }
	        elsif ($actions == 2) 
	        {
	            my $move_cali = pop(@texas);
	            push(@cali, $move_cali);
	            $current_pos = scalar@texas;
	            $current_pos--;
	            $i--;
	            next;
	        }
	        elsif ($actions == 3) {
	            pop(@texas);
	            $current_pos = scalar@texas;
	            $current_pos--;
	            next;
	        }
	        elsif ($actions == 4) {
	            last;
	        }
	        elsif ($actions == 5) {
	            print "Error!\n";
	            last;
	        }
	    }
	}
	#print "@cali\n";
	my $result = postfix_cal(@cali);
	
	return $result;
	
	#print "Answer is: $result\n";
}







#define DMA_32
#define DMA_ENABLE

#defined differently in the book depending on the project you are working on








