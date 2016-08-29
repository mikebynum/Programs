% CSCI 4220
% PROLOG - Program 1
% Mike Bynum
	
%trip(1,7).
%roads([[1,2,500,225,45], [1,3,500,315,135],[2,4,500,315,135], [3,4,500,225,45],[4,5,500,225,45], [4,6,500,315,135],[5,7,500,315,135], [6,7,500,225,45]] ).      
%roads([[2,1,500,45,225], [3,1,500,135,315],[4,2,500,135,315], [4,3,500,45,225],[5,4,500,45,225], [6,4,500,135,315],[7,5,500,135,315], [7,6,500,45,225]] ).
%diameters([1000,1000,1000,1000,400,1000,1000]).

trip(10,3).
diameters( [500, 600, 900, 300, 1200, 750, 620, 350, 900, 800] ).
roads( [[4,1,3000,120,260],
	[3,1,1000,160,310],
	[2,3,1000,290,50],
	[3,4,5500,270,90],
	[4,5,2800,45,190],
	[6,4,2800,160,350],
	[5,6,2800,260,80],
	[5,7,4500,330,350],
	[6,7,3500,190,40],
	[7,8,3000,260,45],
	[9,7,3000,135,300],
	[9,10,1000,0,180]] ).

go :-
	trip(From,To),
	paths(From,To,Route),
	%print(Route), nl,
	add_distance(Route,RoutesDistance),
	%print(Route), print(RoutesDistance),nl,
	sort(RoutesDistance,Ans),
	print('Routes in increasing order:'),nl,
	pprint(Ans).	

%above stuff is given

around(Inangle,Inangle,Diam,Arc) :-
       Arc is 3.14159 * Diam.
around(Inangle, Outangle, Diam, Arc):-
	Inangle == Outangle,
	Arc is 3.14159*Diam.
around(Inangle, Outangle, Diam, Arc):-
	Inangle < Outangle,
	Arc is (Outangle-Inangle)/360*3.14159*Diam.
around(Inangle, Outangle, Diam, Arc):-
	Inangle > Outangle,
	Arc is (360-(Inangle-Outangle))/360*3.14159*Diam.

	
%HERE ARE THE DISTANCE METHODS	


dist([Start,Dest],Dist):- %if there are two places, we only need the direct distance, no roundabouts
	getroad(Start,Dest,Dist,_Outangle,_Inangle).
dist([Start,Dest,Dest2|More],Dist):- % this is the distance between 3 or more points (including roundabouts)	
	dist([Start,Dest],Dist1),
	getroad(Start,Dest,_,_,Inangle),
	getroad(Dest,Dest2,_,Outangle,_),
	diameters(Around),
	nth1(Dest,Around,Diam),
	around(Inangle,Outangle,Diam,Arc),
	Total is Dist1 + Arc,
	dist([Dest,Dest2|More],Dist3),
	Dist is Total+Dist3.
	
getroad(Start,Dest,Dist,Outangle,Inangle):-
	roads(R),
	member([Start,Dest,Dist,Outangle,Inangle],R).	
getroad(Start,Dest,Dist,Outangle,Inangle):-
	roads(R),
	member([Dest,Start,Dist,Inangle,Outangle],R).
	
	
getroute( Begin, End, Route ) :- 
	hop( [Begin], End, RevRoute ),
	reverse( RevRoute, Route ).
hop( [End|Before], End, [End|Before] ). % We are there.
hop( [At|Before], End, FinalRoute ) :-
	getroad(At,NewCity,_Dist,_Inangle,_Outangle),
	\+ member( NewCity, [At|Before]),
	hop( [NewCity,At|Before], End, FinalRoute ).


	
paths( From, To, AllRoutes ) :- 
	allroutes( From, To, [], AllRoutes ).
allroutes( Start, Finish, Sofar, New ) :-
	getroute( Start, Finish, TheRoute ),
	\+ member( TheRoute, Sofar ),
	allroutes( Start, Finish, [TheRoute|Sofar], New ).
allroutes( _, _, AllRoutes, AllRoutes ).

add_distance([],[]).
	%print('ERROR'),nl.
add_distance([Route|More],[[D,Route]|NewMore]):-
	dist(Route, D),
	add_distance(More,NewMore).

pprint([]). 
	%print('DONE'),nl.
pprint( [[F|M]|R] ) :- 
	print( 'Distance= ' ), print(F), print('  Route= '),[Y]=M,print(Y),nl, pprint(R).
	
%cons(A,[X],[A|X]). %cons(42,[a,b,c],V). => V={42,a,b,c}
	
%	trip(A,B).
%	roads(R),
%	member([A,B,Dist,Inangle,Outangle],R),
%	dist([A,B],Dist),
%	print(Dist),nl.

%go:-
%	all_v2
%	add_distances(list of routes, list of routes with distances)
%	sort(list of routes with distances, answer)
%	pprint(ans)
	

