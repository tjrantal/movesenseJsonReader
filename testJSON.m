fclose all;
close all;
clear all;
clc;

javaaddpath('build/libs/MovesenseJSONReader-1.0.jar');

dataPath = 'data/';
fList = dir([dataPath '*.json']);

for f = {fList(:).name}
	jReader = javaObject('timo.home.MoveJSONReader',[dataPath f{1}]);
	stamps = javaMethod('getStamps',jReader);
	acc = [javaMethod('getAX',jReader),javaMethod('getAY',jReader),javaMethod('getAZ',jReader)];
	%figure
	%plot(stamps);
	%keyboard;
	figure
	plot(acc);
	title(f{1});
	%keyboard;
end