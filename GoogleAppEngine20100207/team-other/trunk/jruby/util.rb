class Hash
	##
	## hash = {:name=>'xxx',:age=>123,:etc=>456,:sonota=>"xxx"}
	##
	## hash.pairs_at(:name, :age)
	##     => {:name=>'xxx',:age=>123}
	##
	def pairs_at(*keys)
		keys.zip(values_at(*keys)).inject(self.class.new) {|h, (k, v)|
			v = yield(k, v) if block_given?
			h.merge(k => v)
		}
	end
end

module MySinatraUtil
	##
	##	if request_is_ok?({:to=>"宛先不明",:from=>"送信元不明",:body=>"本文なし"})	
	##
	##
	def request_is_ok?( param_hash )
		is_ok = true
		param_hash.keys.each do |param_key|
			if params[param_key] and params[param_key] != ""
				# ok
			else
				is_ok = false
				if block_given?
					yield param_key,param_hash[param_key]
				end
			end
		end
		is_ok
	end
end
