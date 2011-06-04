package theWorld.AndroidHackathon.Kyoto;

import java.nio.IntBuffer;
import javax.microedition.khronos.opengles.GL10;

/**
 * ※このクラスは未使用 
 *
 */
public class TextureDrawer
{
    /**
     * 2D�e�N�X�`����`�悷��
     *
     * @param gl
     * @param tex_id �e�N�X�`��ID
     * @param x, y �`�悷����W
     * @param width, height �l�p�`�̕��E����
     * @param angle ��]�p�x
     * @param scale_x, scale_y �g�嗦
     */
    static void drawTexture(GL10 gl, int tex_id, float x, float y, int width,
            int height, float angle, float scale_x, float scale_y)
    {
        // �Œ菬���_�l��1.0
        int one = 0x10000;

        // ���_���W
        int vertices[] = {
            -width*one/2, -height*one/2, 0,
            width*one/2, -height*one/2, 0,
            -width*one/2, height*one/2, 0,
            width*one/2, height*one/2, 0,
        };

        // �e�N�X�`�����W�z��
        int texCoords[] = {
            0, one, one, one, 0, 0, one, 0,
        };

        // ���_�z����g�����Ƃ�錾
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        // �e�N�X�`�����W�z����g�����Ƃ�錾
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        // �u�����f�B���O��L��
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        // 2D�e�N�X�`����L���
        gl.glEnable(GL10.GL_TEXTURE_2D);
        // �e�N�X�`�����j�b�g0�Ԃ��A�N�e�B�u��
        gl.glActiveTexture(GL10.GL_TEXTURE0);
        // �e�N�X�`��ID�ɑΉ�����e�N�X�`�����o�C���h
        gl.glBindTexture(GL10.GL_TEXTURE_2D, tex_id);

        // ���f���r���[�s���I��
        gl.glMatrixMode(GL10.GL_MODELVIEW);

        // ���ݑI������Ă���s��(���f���r���[�s��)�ɁA�P�ʍs����Z�b�g
        gl.glLoadIdentity();

        // �s��X�^�b�N�Ɍ��݂̍s����v�b�V��
        gl.glPushMatrix();

        // ���f���𕽍s�ړ�����s����|�����킹��
        gl.glTranslatef(x, y, 0);
        // ���f����Z�����S�ɉ�]����s����|�����킹��
        gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
        // ���f�����g��k������s����|�����킹��
        gl.glScalef(scale_x, scale_y, 1.0f);

        // �F���Z�b�g
        gl.glColor4x(0x10000, 0x10000, 0x10000, 0x10000);
        // ���_���W�z����Z�b�g
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, IntBuffer.wrap(vertices));
        // �e�N�X�`�������Z�b�g
        gl.glTexCoordPointer(2, GL10.GL_FIXED, 0, IntBuffer.wrap(texCoords));

        // �Z�b�g�����z������ɕ`��
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        // �����قǃv�b�V��������Ԃɍs��X�^�b�N��߂�
        gl.glPopMatrix();

        // �L��ɂ������̂𖳌�
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisable(GL10.GL_TEXTURE_2D);
    }
}
