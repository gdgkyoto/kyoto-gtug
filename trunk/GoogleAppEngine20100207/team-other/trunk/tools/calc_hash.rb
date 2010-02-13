require 'digest/sha1'
def calc_hash( email )
	key1 = 'Aoihdoieur8ihjdoia&Atgeliu3ge'
	key2 = '%Ajew78iUWO(RY;ojfwpeo9)SRp3o'
	h1 = Digest::SHA1.hexdigest( key1 + email)
	hash = Digest::SHA1.hexdigest( key2 + h1)
end

ARGV.each do |email|
	puts '-----'
	puts email
	puts calc_hash( email )
end
