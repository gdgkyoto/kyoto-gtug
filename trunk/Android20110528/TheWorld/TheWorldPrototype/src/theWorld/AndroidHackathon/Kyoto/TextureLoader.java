package theWorld.AndroidHackathon.Kyoto;

import javax.microedition.khronos.opengles.GL10;

import java.io.IOException;
import java.io.InputStream;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

/**
 * ※このクラスは未使用 
 *
 */
/**
 * �e�N�X�`���ǂݍ��݂��s���N���X
 */
public class TextureLoader
{
    /**
     * �e�N�X�`����ǂݍ���
     *
     * @param gl
     * @param context �A�N�e�B�r�e�B��n��
     * @param resource_id �ǂݍ��ރ��\�[�X��ID��n��
     * @return ���������e�N�X�`����ID��Ԃ�
     */
    static int loadTexture(GL10 gl, Context context, int resource_id)
    {
        int[] textures = new int[1];
        // �e�N�X�`�����쐬���邽�߂̌ŗL����1�쐬
        gl.glGenTextures(1, textures, 0);

        // �쐬���ꂽ�e�N�X�`����ID���Z�b�g
        int texture_id = textures[0];
        // �w�肵���ŗL�������e�N�X�`�����쐬
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texture_id);

        // �o�C���h����Ă���e�N�X�`���ɁA�e�N�X�`���̊g��E�k����@���w��B
        // �k�������ɂ͍������̂��߂Ƀj�A���X�g�l�C�o�[�@��p����
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
                GL10.GL_NEAREST);
        // �g��͐�`�⊮�Ƃ���B�d���悤�Ȃ�j�A���X�g�l�C�o�[�@(GL_NEAREST)�֕ύX���邱��
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
                GL10.GL_LINEAR);

        // �o�C���h����Ă���e�N�X�`���ɁA�J��Ԃ���@���w��
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
                GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
                GL10.GL_CLAMP_TO_EDGE);

        // �o�C���h����Ă���e�N�X�`���ɁA�e�N�X�`���̐F�����n�̐F��u��������悤�w��(GL_REPLACE)
        gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE,
                GL10.GL_REPLACE);

        // ���\�[�X���I�[�v��
        InputStream is = context.getResources().openRawResource(resource_id);
        Bitmap bitmap;
        try
        {
            // ���\�[�X����r�b�g�}�b�v�f�[�^���쐬
            bitmap = BitmapFactory.decodeStream(is);
        }
        finally
        {
            try
            {
                is.close();
            }
            catch (IOException e)
            {
                // ��O����
            }
        }

        // �r�b�g�}�b�v����e�N�X�`�����쐬����
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        // �s�v�ɂȂ����r�b�g�}�b�v�f�[�^���J���
        bitmap.recycle();

        // �e�N�X�`���ɃA�N�Z�X���邽�߂�ID��Ԃ�
        return texture_id;
    }
}
