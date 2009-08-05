require 'rubygems'
require 'sinatra'
require 'bumble'
require 'time'

class Person
	include Bumble
	ds :content
end

get '/' do
	@persons = Person.all
	erb :index
end

post '/' do
	Person.create(params['person'])
	redirect '/'
end
