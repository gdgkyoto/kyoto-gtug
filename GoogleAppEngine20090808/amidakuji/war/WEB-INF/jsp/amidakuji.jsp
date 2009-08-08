<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" />
		<title>���݂�����</title>
		<script type="text/javascript" src="./prototype.js"></script>
		<script type="text/javascript" src="./wz_jsgraphics.js"></script>
<style type="text/css">
<!--
div#container {
	margin : 0 auto;
	padding : 0;
	width : 500px;
	height : 200px;
}
div#container table {
	border-collapse : collapse;
	border-spacing : 0;
	width : 100%;
}
div#container table.buttons {
}
div#container table.amida {
	height : 100%;
}
div#container table tr td.pole {
	background : #999;
}
div#container table tr td.pole.pole_on {
	background : #f00;
}
div#container table tr td.line {
	border-top : 1px solid #fff;
	border-bottom : 1px solid #fff;
}
div#container table tr td.line.line_over {
	background : #9f9;
	cursor : pointer;
}
div#container table tr td.line.line_on {
	background : #f99;
}
div#container table tr img.image {
	border: 3px solid #fff;
	width : 50%;
	height : 50%;
}
div#container table tr img.image_on,
table#amitabha img.image:hover,
table#amitabha img.image:active {
	border: 3px solid #f00;
}
div#rest {
	text-align : right;
}
div#log {
	position : absolute;
	z-index : 100;
}
-->
</style>
<script type="text/javascript">
<!--
Array.prototype.remove = function(obj) {
  var a = [];
  for (var i=0; i<this.length; i++) {
    if (this[i] != obj) {
      a.push(this[i]);
    }
  }
  return a;
};
(function() {
//
// �����N���X��`����
//
	/**
	 * ���O���[�e�B���e�B
	 */
	var NameUtil;
	(function() {
		var _NameUtil = Class.create({
			/**
			 * �R���X�g���N�^
			 */
			initialize : function() {
			},
			getTitleId : function() {
				return 'title';
			},
			getDrawLineUrl : function() {
				return './putLine';
			},
			getReloadUrl : function() {
				return './getAmidaData';
			},
			getContainerId : function() {
				return 'container';
			},
			getAmidaTableClassName : function() {
				return 'amida';
			},
			getButtonTableClassName : function() {
				return 'buttons';
			},
			getImageTableClassName : function() {
				return 'images';
			},
			getRestTimeId : function() {
				return 'time';
			},
			/**
			 * �s�E��̃C���f�b�N�X����Z����ID�𐶐�����
			 * @param inR �s�̃C���f�b�N�X
			 * @param inC ��̃C���f�b�N�X
			 * @return �Z����ID
			 */
			getPoleId : function(inR,inC) {
				return 'pole_'+inR+'_'+inC;
			},
			getPoleClassName : function() {
				return 'pole';
			},
			getPoleOnClassName : function() {
				return 'pole_on';
			},
			getLineId : function(inR,inC) {
				return 'line_'+inR+'_'+inC;
			},
			getLineClassName : function() {
				return 'line';
			},
			getLineOverClassName : function() {
				return 'line_over';
			},
			getLineOnClassName : function() {
				return 'line_on';
			},
			getImageId : function(inC) {
				return 'image_' + inC;
			},
			getImageClassName : function() {
				return 'image';
			},
			getImageOverClassName : function() {
				return 'image_over';
			},
			getImageOnClassName : function() {
				return 'image_on';
			}
		});
		NameUtil = new _NameUtil();
	})();

	/**
	 * ���O���[�e�B���e�B
	 */
	var LogUtil;
	(function() {
		var _LogUtil = Class.create({
			/**
			 * �R���X�g���N�^
			 */
			initialize : function() {
			},
			writeLog : function(inM) {
				var div = new Element('div');
				div.appendChild(document.createTextNode(inM));
				$('log').insert(div);
			}
		});
		LogUtil = new _LogUtil();
	})();

	/**
	 * ���݂������N���X
	 */
	var Amida = Class.create({
		/**
		 * �R���X�g���N�^
		 * @param option �����p�����[�^
		 */
		initialize : function(option) {
		},
		setTitle : function(inT) {
			$(NameUtil.getTitleId()).innerHTML = inT;
		},
		/**
		 * �c�莞�Ԃ�ݒ肷��
		 * �������ɃJ�E���g�_�E���̃^�C�}�[���X�^�[�g����
		 * @param �c�莞��
		 */
		setRestTime : function(inR) {
			this.restTime = inR;
			if(this._rest_timer) this._rest_timer.stop();
			this._rest_timer = new PeriodicalExecuter(function() {
				this.restTime -= 1000
				$(NameUtil.getRestTimeId()).innerHTML = (this.restTime/1000);
				if(!this.restTime) {
					this._rest_timer.stop();
				}
			}.bind(this),1);
		},
		/**
		 * ���[�U�����擾����
		 * @return ���[�U��
		 */
		getNumUsers : function() {
			return this.users.length;
		},
		/**
		 * ���[�UID���烆�[�U���擾����
		 * @param inId ���[�UID
		 * @return ���[�U
		 */
		getUser : function(inId) {
			if(inId === undefined) inId = this.userId;
			return this.users.find(function(user,index) {
				return user.getId() == inId;
			});
		},
		/**
		 * �C���f�b�N�X���烆�[�U���擾����
		 * @param inI �C���f�b�N�X
		 * @return ���[�U
		 */
		getUserByIndex : function(inI) {
			return this.users[inI];
		},
		/**
		 * �C���f�b�N�X����摜���擾����
		 * @param inI �C���f�b�N�X
		 * @return �摜
		 */
		getImageByIndex : function(inI) {
			return this.images[inI];
		},
		/**
		 * ���C��������
		 * @param inL �ǉ����郉�C��
		 */
		drawLine : function(inL) {
			this.status.lines.push(inL);
			this.status.points.push(inL.getLeftPoint());
			this.status.points.push(inL.getRightPoint());
			inL.getHtmlElement().setStyle({'backgroundColor':this.getUser(inL.getUserId()).getLineColor()});
		},
		/**
		 * ���C����ǂݍ���
		 * @param ���C���̃��X�g
		 */
		loadLine : function(lines) {
			if(!lines) return;
			if(!lines.each) lines = $A(lines);
			lines.each(function(line,index) {
				this.drawLine(line);
			}.bind(this));
		},
		loadPoint : function(points) {
			if(!points) return;
			if(!points.each) points = $A(points);
			points.each(function(point,index) {
				point.getHtmlElement().setStyle({'backgroundColor':this.getUser(point.getUserId()).getLineColor()});
			}.bind(this));
		},
		/**
		 * ���[�g�\��������
		 */
		crearRoutes : function() {
			this.elements.poles.each(function(poleCell,index) {
				poleCell.removeClassName(NameUtil.getPoleOnClassName());
			});
			this.elements.images.each(function(image,index) {
				image.removeClassName(NameUtil.getImageOnClassName());
			});
		},
		/**
		 * ���C�����擾����
		 * @param inP ���C���̒[�|�C���g
		 * @return �|�C���g�����L���郉�C��
		 */
		getLine : function(inP) {
			for(var i=0; i<this.status.lines.length; ++i) {
				var line = this.status.lines[i];
				if(line.getLeftPoint().equals(inP)) return line;
				if(line.getRightPoint().equals(inP)) return line;
			}
			return;
		},
		/**
		 * ���̃|�C���g���擾����
		 * @param inP �|�C���g
		 * @return ���̃|�C���g
		 */
		getNextPoint : function(inP) {
			var line = this.getLine(inP);
			if(!line) {
				if(this.numRows <= inP.getRow()+1) return;
				return new Point(inP.getRow()+1,inP.getCol());
			}
			if(line.getLeftPoint().equals(inP)) {
				var _p = line.getRightPoint();
				if(this.numRow <= _p.getRow()+1) return;
				return new Point(_p.getRow()+1,_p.getCol());
			} else {
				var _p = line.getLeftPoint();
				if(this.numRows <= _p.getRow()+1) return;
				return new Point(_p.getRow()+1,_p.getCol());
			}
		},
		setLastEnable : function(inB) {
			this.lastEnable = inB;
			var color;
			if(inB) {
				color = 'white';
			} else {
				color = 'gray';
			}
			this.elements.lines.each(function(line) {
				var row = parseInt(line.getAttribute('row'));
				if(this.numRowsFinal <= row) line.setStyle({'backgroundColor':color});
			}.bind(this));
		},
		/**
		 * ���C�����N���b�N�ł��邩�ǂ�����Ԃ�
		 * @return ���C�����N���b�N�ł��邩�ǂ���
		 */
		isClickable : function(inL) {
			if(!this.isRunning) return false;
			if(!this.lineEnable) return false;
			if(!this.lastEnable) {
				if(this.numRowsFinal <= inL.getRow()) {
					return false;
				}
			}
			for(var i=0; i<this.status.points.length; ++i) {
				var point = this.status.points[i];
				if(inL.getLeftPoint().equals(point)) return false;
				if(inL.getRightPoint().equals(point)) return false;
			}
			for(var i=0; i<this.status.waitPoints.length; ++i) {
				var point = this.status.waitPoints[i];
				if(inL.getLeftPoint().equals(point)) return false;
				if(inL.getRightPoint().equals(point)) return false;
			}
			return true;
		},
		/**
		 * ���������\�b�h
		 * @param option �p�����[�^
		 */
		init : function(option) {
			this.id = option.id;
			this.numRows = option.numRows;
			this.numRowsFinal = option.numRowsFinal;
			this.reloadDelay = option.reloadDelay;
			this.routeDelay = option.routeDelay;
			this.users = option.users;
			this.images = option.images;
			this.userId = option.userId;
			this.setTitle(option.title);

			//
			// ���݂���������
			//
			this.elements = {};
			this.status = {};
			this.status.points = $A();
			this.status.lines = $A();
			this.status.waitPoints = $A();
			this.status.waitLines = $A();

			var container = $(NameUtil.getContainerId());

			var routeTimer;
			(function() {
				//
				// �N���A�{�^���z�u
				//
				var div = new Element('div');
				div.setStyle({'textAlign':'right'});
				container.insert(div);
				var button = new Element('button');
				button.appendChild(document.createTextNode('�N���A'));
				div.insert(button);
				Event.observe(button,'click',function(inE) {
					if(routeTimer) routeTimer.stop();
					this.crearRoutes();
				}.bind(this));
			}.bind(this))();

			(function() {
				//
				// �{�^���z�u
				//
				this.elements.buttons = $A();
				var buttonTable = new Element('table');
				buttonTable.addClassName(NameUtil.getButtonTableClassName());
				container.insert(buttonTable);
				var tr = $(buttonTable.insertRow(0));
				var numCols = this.getNumUsers();
				for(var j=0; j<numCols; ++j) {
					var buttonCell = $(tr.insertCell(j));
					buttonCell.setStyle({'textAlign':'center'});
					buttonCell.setStyle({'width':(100/numCols)+'%'});
					var _user = this.getUserByIndex(j);
					var button = new Element('button');
					button.appendChild(document.createTextNode(_user.getName()));
					buttonCell.appendChild(button);
					//
					// ���[�g�\���A�j���[�V�����o�^
					// �^�C�}�͂��ׂẴ{�^���ŋ��ʂƂ���
					//
					(function() {
						var user = _user;
						Event.observe(button,'click',function(inE) {
							if(routeTimer) routeTimer.stop();
							this.crearRoutes();
							var point = user.getStartPoint();
							routeTimer = new PeriodicalExecuter(function() {
								if(!this.lastEnable) {
									if(this.numRowsFinal <= point.getRow()) {
										routeTimer.stop();
										point = null;
										return;
									}
								}
								point.getHtmlElement().addClassName(NameUtil.getPoleOnClassName());
								var _point = this.getNextPoint(point);
								if(!_point) {
									routeTimer.stop();
									var img = this.getImageByIndex(point.getCol()).getHtmlElement();
									img.addClassName(NameUtil.getImageOnClassName());
								}
								point = _point;
							}.bind(this),this.routeDelay);
						}.bind(this));
					}.bind(this))();
					this.elements.buttons.push(button);
				}
			}.bind(this))();

			(function() {
				//
				// ���C���z�u
				//
				this.elements.poles = $A();
				this.elements.lines = $A();
				var amidaTable = new Element('table');
				amidaTable.addClassName(NameUtil.getAmidaTableClassName());
				container.insert(amidaTable);
				var numCols = this.getNumUsers();
				for(var i=0; i<this.numRows; ++i) {
					var tr = $(amidaTable.insertRow(i));
					var leftDummyCell = $(tr.insertCell(0));
					leftDummyCell.setStyle({'width':(100/numCols)*0.45+'%'});
					for(var j=0; j<numCols; ++j) {
						var index = j*2;
						var poleCell = $(tr.insertCell(index+1));
						poleCell.setAttribute('id',NameUtil.getPoleId(i,j));
						poleCell.addClassName(NameUtil.getPoleClassName());
						poleCell.setAttribute('row',i);
						poleCell.setAttribute('col',j);
						poleCell.setStyle({'width':(100/numCols)*0.1+'%'});
						this.elements.poles.push(poleCell);
						if(j != numCols-1) {
							var lineCell = $(tr.insertCell(index+2));
							lineCell.setAttribute('id',NameUtil.getLineId(i,j));
							lineCell.addClassName(NameUtil.getLineClassName());
							lineCell.setAttribute('row',i);
							lineCell.setAttribute('col',j);
							lineCell.setStyle({'width':(100/numCols)*0.9+'%'});
							this.elements.lines.push(lineCell);

							Event.observe(lineCell,'click',function(inE) {
								var cell = Event.element(inE);
								var line = new Line(cell.getAttribute('row'),cell.getAttribute('col'),this.userId);
								if(!this.isClickable(line)) return;
								cell.addClassName(NameUtil.getLineOnClassName());
								//
								// Ajax
								//  success : ���C����o�^,�ҋ@���C�����폜
								//  failure : �ҋ@���C�����폜
								var leftPoint = line.getLeftPoint();
								var rightPoint = line.getRightPoint();
								this.status.waitPoints.push(leftPoint);
								this.status.waitPoints.push(rightPoint);
								this.status.waitLines.push(line);
								LogUtil.writeLog('write-line start');
								var params = ('id='+this.id+'&x='+line.getCol()+'&y='+line.getRow());
								LogUtil.writeLog(params);
								var ajax = new Ajax.Request(NameUtil.getDrawLineUrl(),
									{
										'method' : 'get',
										'parameters' : params,
										onSuccess : function(request) {
											LogUtil.writeLog('write-line onSuccess');
											var _left = leftPoint;
											var _right = rightPoint;
											var _line = line;
											var _cell = cell;
											var obj = request.responseJSON;
											if(obj.result) {
												this.drawLine(_line);
											} else {
												_cell.removeClassName(NameUtil.getLineOnClassName());
											}
											this.status.waitPoints = this.status.waitPoints.remove(_left);
											this.status.waitPoints = this.status.waitPoints.remove(_right);
											this.status.waitLines = this.status.waitLines.remove(_line);
										}.bind(this),
										onFailure : function(request) {
											LogUtil.writeLog('write-line onFailure');
											var _left = leftPoint;
											var _right = rightPoint;
											var _line = line;
											var _cell = cell;
											this.status.waitPoints = this.status.waitPoints.remove(_left);
											this.status.waitPoints = this.status.waitPoints.remove(_right);
											this.status.waitLines = this.status.waitLines.remove(_line);
											_cell.removeClassName(NameUtil.getLineOnClassName());
										}.bind(this),
										onException : function(request) {
											LogUtil.writeLog('write-line onException');
											var _left = leftPoint;
											var _right = rightPoint;
											var _line = line;
											var _cell = cell;
											this.status.waitPoints = this.status.waitPoints.remove(_left);
											this.status.waitPoints = this.status.waitPoints.remove(_right);
											this.status.waitLines = this.status.waitLines.remove(_line);
											_cell.removeClassName(NameUtil.getLineOnClassName());
										}.bind(this)
									}
								);
							}.bind(this));
							Event.observe(lineCell,'mouseover',function(inE) {
								var cell = Event.element(inE);
								var line = new Line(cell.getAttribute('row'),cell.getAttribute('col'));
								if(!this.isClickable(line)) return;
								cell.addClassName(NameUtil.getLineOverClassName());
							}.bind(this));
							Event.observe(lineCell,'mouseout',function(inE) {
								var cell = Event.element(inE);
								var line = new Line(cell.getAttribute('row'),cell.getAttribute('col'));
								cell.removeClassName(NameUtil.getLineOverClassName());
							}.bind(this));
						}
					}
					var rightDummyCell = $(tr.insertCell(numCols*2));
					rightDummyCell.setStyle({'width':(100/numCols)*0.45+'%'});
				}
			}.bind(this))();

			(function() {
				//
				// �摜�z�u
				//
				this.elements.images = $A();
				var imageTable = new Element('table');
				imageTable.addClassName(NameUtil.getImageTableClassName());
				container.insert(imageTable);
				var tr = $(imageTable.insertRow(0));
				var numCols = this.getNumUsers();
				for(var j=0; j<numCols; ++j) {
					var imageCell = $(tr.insertCell(j));
					imageCell.setStyle({'textAlign':'center'});
					imageCell.setStyle({'width':(100/numCols)+'%'});
					var image = this.getImageByIndex(j).createHtmlElement();
					image.setAttribute('id',NameUtil.getImageId(j));
					image.addClassName(NameUtil.getImageClassName());
					imageCell.insert(image);
					this.elements.images.push(image);
				}
			}.bind(this))();
		},
		/**
		 * �J�n���\�b�h
		 * @param option �p�����[�^
		 */
		start : function(option) {

			this.loadOption(option);

			if(this.isRunning) {
				if(this._reload_timer) this._reload_timer.stop();
				this._reload_timer = new PeriodicalExecuter(function() {
					LogUtil.writeLog('reload start');
					var params = ('id='+this.id+'&lastId='+this.lastLineId);
					LogUtil.writeLog(params);
					var ajax = new Ajax.Request(NameUtil.getReloadUrl(),
						{
							'method' : 'get',
							'parameters' : params,
							onSuccess : function(request) {
								LogUtil.writeLog('reload success');
								var option = {};
								var obj = request.responseJSON;
								var _ls = obj.lines;
								if(_ls) {
									var lines = $A();
									for(var i=0; i<_ls.length; ++i) {
										var _l = _ls[i];
										lines.push(new Line(_l[1],_l[1],_l[2]));
									}
									option.lines = lines;
								}
								var _cps = obj.currentPosition;
								if(_cps) {
									var currentPoints = $A();
									for(var i=0; i<_cps.length; ++i) {
										var _cp = _cps[i];
										currentPoints.push(new Point(_cp[1],_cp[0],_cp[2]));
									}
									option.currentPoints = currentPoints;
								}
								option.isRunning = !obj.finished;
								option.lastLineId = obj.lastLineId;
								option.restTime = obj.leftTime;
								option.lastEnable = obj.lastEnable;
								option.lineEnable = obj.lineEnable;
								this.loadOption(option);
							}.bind(this),
							onFailure : function(request) {
								LogUtil.writeLog('reload failure');
							}.bind(this),
							onException : function(request) {
								LogUtil.writeLog('reload onException');
							}.bind(this)
						}
					);
					if(!this.isRunning) {
						this._rest_timer.stop();
					}
				}.bind(this),this.reloadDelay/1000);
			}
		},
		/**
		 * �p�����[�^��ǂݍ���
		 * @param option �p�����[�^
		 */
		loadOption : function(option) {
			this.loadPoint(option.currentPoints);
			this.loadLine(option.lines);
			this.currentRow = option.currentRow;
			this.setRestTime(option.restTime);
			this.isRunning = option.isRunning;
			this.lastLineId = option.lastLineId;
			this.setLastEnable(option.lastEnable);
			this.lineEnable = option.lineEnable;
		}
	});

	/**
	 * ���[�U�N���X
	 */
	var User = Class.create({
		/**
		 * �R���X�g���N�^
		 * @param option �����p�����[�^
		 */
		initialize : function(option) {
			this.id = option.id;
			this.name = option.name;
			this.startPoint = option.startPoint;
			this.poleColor = option.poleColor;
			this.lineColor = option.lineColor;
		},
		/**
		 * ID���擾����
		 * @return ID
		 */
		getId : function() {
			return this.id;
		},
		/**
		 * ���O���擾����
		 * @return ���O
		 */
		getName : function() {
			return this.name;
		},
		/**
		 * �J�n�|�C���g���擾����
		 * @return �J�n�|�C���g
		 */
		getStartPoint : function() {
			return this.startPoint;
		},
		/**
		 * ���C���̐F���擾����
		 * @return ���C���̐F
		 */
		getLineColor : function() {
			return this.lineColor;
		}
	});

	/**
	 * �摜���N���X
	 */
	var Image = Class.create({
		/**
		 * �R���X�g���N�^
		 * @param option �����p�����[�^
		 */
		initialize : function(option) {
			this.id = option.id;
			this.url = option.url;
			this.title = option.title;
		},
		/**
		 * �摜��Html�v�f�𐶐�����
		 * @return �摜��Html�v�f
		 */
		createHtmlElement : function() {
			var img = new Element('img');
			img.setAttribute('src',this.url);
			img.setAttribute('id',NameUtil.getImageId(this.id));
			img.setAttribute('title',this.title);
			img.addClassName(NameUtil.getImageClassName());
			return img;
		},
		/**
		 * �摜��Html�v�f���擾����
		 * @return �摜��Html�v�f
		 */
		getHtmlElement : function() {
			return $(NameUtil.getImageId(this.id));
		}
	});

	/**
	 * �ʒu���N���X
	 */
	var Point = Class.create({
		/**
		 * �R���X�g���N�^
		 * @param inR �s
		 * @param inC ��
		 */
		initialize : function(inR,inC,inI) {
			this.row = parseInt(inR);
			this.col = parseInt(inC);
			this.userId = inI;
		},
		/**
		 * ����擾����
		 * @return ��
		 */
		getRow : function() {
			return this.row;
		},
		/**
		 * �s���擾����
		 * @return �s
		 */
		getCol : function() {
			return this.col;
		},
		/**
		 * ���[�UID���擾����
		 * @return ���[�UID
		 */
		getUserId : function() {
			return this.userId;
		},
		/**
		 * �ʒu��Html�v�f���擾����
		 * @return �ʒu��Html�v�f
		 */
		getHtmlElement : function() {
			if((this.getRow() || this.getRow()===0) && (this.getCol() || this.getCol()===0)) {
				return $(NameUtil.getPoleId(this.getRow(),this.getCol()));
			}
		},
		/**
		 * �ʒu���������ǂ������`�F�b�N����
		 * @return �ʒu�������ꍇ��true
		 */
		equals : function(inP) {
			return ((this.getRow() == inP.getRow()) && (this.getCol() == inP.getCol()));
		}
	});

	/**
	 * ���C�����N���X
	 */
	var Line = Class.create({
		/**
		 * �R���X�g���N�^
		 * @param inR �s
		 * @param inC ��
		 */
		initialize : function(inR,inC,inI) {
			this.row = parseInt(inR);
			this.col = parseInt(inC);
			this.userId = inI;
		},
		/**
		 * ���[�UID���擾����
		 * @return ���[�UID
		 */
		getUserId : function() {
			return this.userId;
		},
		/**
		 * ����擾����
		 * @return ��
		 */
		getRow : function() {
			return this.row;
		},
		/**
		 * �s���擾����
		 * @return �s
		 */
		getCol : function() {
			return this.col;
		},
		/**
		 * ���C����Html�v�f���擾����
		 * @return ���C����Html�v�f
		 */
		getHtmlElement : function() {
			if((this.getRow() || this.getRow()===0) && (this.getCol() || this.getCol()===0)) {
				return $(NameUtil.getLineId(this.getRow(),this.getCol()));
			}
		},
		/**
		 * ���C���̍��[�̃|�C���g���擾����
		 * @return ���C���̍��[�̃|�C���g
		 */
		getLeftPoint : function() {
			return new Point(this.getRow(),this.getCol());
		},
		/**
		 * ���C���̉E�[�̃|�C���g���擾����
		 * @return ���C���̉E�[�̃|�C���g
		 */
		getRightPoint : function() {
			return new Point(this.getRow(),this.getCol()+1);
		},
		/**
		 * ���C�����������ǂ������`�F�b�N����
		 * @return ���C���������ꍇ��true
		 */
		equals : function(inL) {
			return (this.getLeftPoint().equals(inL.getLeftPoint()) && this.getRightPoint().equals(inL.getRightPoint()));
		}
	});
//
// �����N���X��`����
//


	//
	// �I�����[�h�C�x���g�o�^
	//
	Event.observe(document,'dom:loaded',function(inE) {
//
// ���������ݒ聥��
//
		var amida = new Amida();
		(function() {
			// ���[�U���X�g
			var colors = ['blue','pink','yellow','red','gray'];
			var users = [
			 	<c:forEach var="user" items="${users}" varStatus="status">
			 		<c:if test="${status.index != 0}">
			 			,
			 		</c:if>
					new User({'id':'${user}','name':'${user}','startPoint':new Point(0,${status.index}),'lineColor':colors[${status.index}]})
				</c:forEach>
/*
				new User({'id':'0','name':'name0','startPoint':new Point(0,0),'lineColor':'pink'}),
				new User({'id':'1','name':'name1','startPoint':new Point(0,1),'lineColor':'blue'}),
				new User({'id':'2','name':'name2','startPoint':new Point(0,2),'lineColor':'green'}),
				new User({'id':'3','name':'name3','startPoint':new Point(0,3),'lineColor':'gray'})
*/
			];
			// �摜���X�g
			var images = [
			 	<c:forEach var="image" items="${images}" varStatus="status">
			 		<c:if test="${status.index != 0}">
			 			,
			 		</c:if>
					new Image({'id':'${status.index}','url':'${image}','title':'${status.index}'})
				</c:forEach>
/*
				/new Image({'id':'0','url':'http://www.aa-movie.com/image/index/index_AA.jpg','title':'t'}),
				new Image({'id':'1','url':'http://www.aa-movie.com/image/index/index_AA.jpg','title':'t'}),
				new Image({'id':'2','url':'http://www.aa-movie.com/image/index/index_AA.jpg','title':'t'}),
				new Image({'id':'3','url':'http://www.aa-movie.com/image/index/index_AA.jpg','title':'t'}),
				new Image({'id':'4','url':'http://www.aa-movie.com/image/index/index_AA.jpg','title':'t'})
*/
			];
			var option = {
				'id' : '<c:out value="${id}"/>',
				'title' : '<c:out value="${title}"/>',
				'numRows' : <c:out value="${length}"/>,
				'numRowsFinal' : <c:out value="${lastLength}"/>,
				'reloadDelay' : <c:out value="${sycInterval}"/>,
				'routeDelay' : 0.02,
				'users' : users,
				'images' : images
			};
			amida.init(option);
		})();

		(function() {
			// ���݈ʒu
			var points = [
			 	<c:forEach var="point" items="${currentPosition}" varStatus="status">
			 		<c:if test="${status.index != 0}">
			 			,
			 		</c:if>
			 			new Point(${point[1]},${point[0]},'${point[2]}')
				</c:forEach>
			];
			// �������C�����X�g
			var lines = [
			 	<c:forEach var="line" items="${lines}" varStatus="status">
			 		<c:if test="${status.index != 0}">
			 			,
			 		</c:if>
					new Line(${line[1]},${line[0]},'${line[2]}')
			 	</c:forEach>
			];
			var option = {
				'restTime' : (<c:out value="${leftTime}"/>),
				'isRunning' : !<c:out value="${finished}"/>,
				'lastLineId' : 1,
				'currentPoints' : points,
				'lines' : lines,
				'lastEnable' : false,
				'lineEnable' : true
			};
			amida.start(option);
		})();
//
// ���������ݒ聢��
//
	});

	/**
	 * ���O��\������
	 * @param inM ���b�Z�[�W
	 */
	var log = function(inM) {
		var div = new Element('div');
		div.appendChild(document.createTextNode(inM));
		$('log').appendChild(div);
	};
})();
//-->
</script>
	</head>
	<body>
		<h2 id="title"></h2>
		<div>
			�Z�����N���b�N���Đ��������܂��B<br />
			�{�^���������ƌo�H�ɐF���t���܂��B
		</div>
		<br />
		<br />
		<div id="rest">�c��<span id="time"></span>�b</div>
		<div id="container"></div>
		<div id="log"></div>
	</body>
</html>
