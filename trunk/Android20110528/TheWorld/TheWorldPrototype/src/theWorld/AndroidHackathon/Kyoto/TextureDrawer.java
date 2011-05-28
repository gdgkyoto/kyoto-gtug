package theWorld.AndroidHackathon.Kyoto;

import java.nio.IntBuffer;
import javax.microedition.khronos.opengles.GL10;

public class TextureDrawer
{
    /**
     * 2Dテクスチャを描画する
     *
     * @param gl
     * @param tex_id テクスチャID
     * @param x, y 描画する座標
     * @param width, height 四角形の幅・高さ
     * @param angle 回転角度
     * @param scale_x, scale_y 拡大率
     */
    static void drawTexture(GL10 gl, int tex_id, float x, float y, int width,
            int height, float angle, float scale_x, float scale_y)
    {
        // 固定小数点値で1.0
        int one = 0x10000;

        // 頂点座標
        int vertices[] = {
            -width*one/2, -height*one/2, 0,
            width*one/2, -height*one/2, 0,
            -width*one/2, height*one/2, 0,
            width*one/2, height*one/2, 0,
        };

        // テクスチャ座標配列
        int texCoords[] = {
            0, one, one, one, 0, 0, one, 0,
        };

        // 頂点配列を使うことを宣言
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        // テクスチャ座標配列を使うことを宣言
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        // ブレンディングを有効化
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        // 2Dテクスチャを有効に
        gl.glEnable(GL10.GL_TEXTURE_2D);
        // テクスチャユニット0番をアクティブに
        gl.glActiveTexture(GL10.GL_TEXTURE0);
        // テクスチャIDに対応するテクスチャをバインド
        gl.glBindTexture(GL10.GL_TEXTURE_2D, tex_id);

        // モデルビュー行列を選択
        gl.glMatrixMode(GL10.GL_MODELVIEW);

        // 現在選択されている行列(モデルビュー行列)に、単位行列をセット
        gl.glLoadIdentity();

        // 行列スタックに現在の行列をプッシュ
        gl.glPushMatrix();

        // モデルを平行移動する行列を掛け合わせる
        gl.glTranslatef(x, y, 0);
        // モデルをZ軸中心に回転する行列を掛け合わせる
        gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
        // モデルを拡大縮小する行列を掛け合わせる
        gl.glScalef(scale_x, scale_y, 1.0f);

        // 色をセット
        gl.glColor4x(0x10000, 0x10000, 0x10000, 0x10000);
        // 頂点座標配列をセット
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, IntBuffer.wrap(vertices));
        // テクスチャ情報をセット
        gl.glTexCoordPointer(2, GL10.GL_FIXED, 0, IntBuffer.wrap(texCoords));

        // セットした配列を元に描画
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        // さきほどプッシュした状態に行列スタックを戻す
        gl.glPopMatrix();

        // 有効にしたものを無効化
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisable(GL10.GL_TEXTURE_2D);
    }
}
