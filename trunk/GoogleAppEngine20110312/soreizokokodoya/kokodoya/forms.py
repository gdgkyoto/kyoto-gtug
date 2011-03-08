# -*- coding: utf-8 -*-

"""
kokodoya.forms

jqueryでデータPOSTするかもしれないけど一応...
"""

from kay.utils.forms.modelform import ModelForm
from kokodoya.models import MeasuredResult

class MeasuredResultForm(ModelForm):
    """測定結果のモデルフォーム.

    フォームに使われるフィールドはMeasuredResultクラスを参照

    """
    class Meta:
        model = MeasuredResult
        exclude = () #除外するフィールド

