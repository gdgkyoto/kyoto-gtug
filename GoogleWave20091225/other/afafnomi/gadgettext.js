<module>  
<moduleprefs title="Sample003">  
    <require feature="rpc">   
</require></moduleprefs>  
<content type="html">  
    <script type="text/javascript" src="http://wave-api.appspot.com/public/wave.js"></script>  
        <input type="button" value="Chage State" onclick="clickBtn()">  
        <script type="text/javascript">
            var count = 0;

            /* Stateが更新された時に呼び出される */
            function stateUpdated() {
                alert("Updated State:" + wave.getState().get('stateCount'));
            }
            /* 状態の更新 */
            function clickBtn(){
                count = count + 1;
                wave.getState().submitDelta({'stateCount': count})
            }
            /* メインメソッド */
            function main() {
                if (wave && wave.isInWaveContainer()) {
                    wave.setStateCallback(stateUpdated)
                }
            }

            /* Wave Gadgetが起動する際に呼び出す */
            gadgets.util.registerOnLoadHandler(main);

        </script>
    ]]>
</content>
</module>
svn import https://kyoto-gtug.googlecode.com/svn/trunk/GoogleWave20091225/other/afafnomi