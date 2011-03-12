# -*- coding: utf-8 -*-

from kay.utils import forms
from kay.utils.forms.modelform import ModelForm

from myone.models import Record

class RecordForm(ModelForm):
  class Meta:
    model = Record
    exclude = ('user', 'created')
