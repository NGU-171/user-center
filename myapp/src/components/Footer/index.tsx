import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';

const Footer: React.FC = () => {
  const defaultMessage = 'NGU-171用户中心';
  const currentYear = new Date().getFullYear();
  return (
    <DefaultFooter
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        {
          key: 'NGU',
          title: 'Never Give Up',
          href: 'https://space.bilibili.com/322180413?spm_id_from=333.1007.0.0',
          blankTarget: true,
        },
        {
          key: 'github',
          title: <GithubOutlined />,
          href: 'https://github.com/NGU-171',
          blankTarget: true,
        },
      ]}
    />
  );
};

export default Footer;
