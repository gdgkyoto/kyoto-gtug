require 'model'

# Strategyパターンを利用して、関連の計算を切り替えられるように実装
#
# 連結計算クラス（基底）
class CalcAssociation
  
  attr_reader :group1, :group2
  
  def initialize(group1, group2)
    @group1, @group2 = group1, group2
  end
  
  def calc()
    raise "Error, Implement method!"
  end
end


class CountCalcAssociation
  def calc()
    overlap_users = []

    group1.users.each do |user1|
      group2.users.each do |user2|
        overlap_users << user1 if user1 == user2
      end
    end
    
    Association.create({
      :group1group2 => user1 + ":" + user2,
      :value => overlap_users.count
    })  
end

#グループのユーザリスト(twitter api のキャッシュ的存在)
#class GroupUserList< TinyDS::Base
#	property	    :groupname      ,:string
#	property	    :users          ,:list       #ユーザ(文字列)の集合
#	property	    :count          ,:integer    #ユーザ数

#	property	    :swcp           ,:string     #ソフトウェアチェックポイント
#end

# 密度で連結の強さを計算する
class DenstinyCalcAssociation
  def calc()
    count = 0

    group1.users.each do |user1|
      group2.users.each do |user2|
        count = count + 1 if user1 == user2
      end
    end
    
    Association.create({
      :group1group2 => user1 + ":" + user2,
      :value => count/group1.users.count + count/group2.users.count
    })
  end
end



class AssociationContext
  def initialize(assoc)
    @assoc = assoc
  end
  
  def make_assoc
    @assoc.calc
  end
end





def calculate_association
  assocs = []
  
  @users = UserDetailList.query.all
  @users.each_index do |i|
    @users.each_index do |j|
      next if j <= i # G1とG2の計算が終わったらG2とG1を計算しないための制御
      assocs << AssociationContext.new( DenstinyCalcAssociation(@user[i], @user[j]) )
    end
  end

  assocs.each {|assoc| assoc.make_assoc }
end
