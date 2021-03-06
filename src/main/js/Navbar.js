import React from 'react';
import PropTypes from 'prop-types';
import {withStyles} from 'material-ui/styles';
import AppBar from 'material-ui/AppBar';
import Toolbar from 'material-ui/Toolbar';
import Typography from 'material-ui/Typography';
import Button from 'material-ui/Button';
import IconButton from 'material-ui/IconButton';
import MenuIcon from 'material-ui-icons/Menu';
import Map from './Map'

const styles = {
    root: {
        width: '100%',
        height: '100%'
    },
    flex: {
        flex: 1,
    },
    position:
        'relative',
    menuButton: {
        marginLeft: -12,
        marginRight: 20,
    },
};

function DashboardAppBar(props) {
    const {classes} = props;
    return (
        <div className={classes.root}>
            <AppBar position="static">
                <Toolbar>
                    <IconButton className={classes.menuButton} aria-label="Menu">
                        <MenuIcon/>
                    </IconButton>
                    <Typography type="title" color="inherit" className={classes.flex}>
                        Sun Electric
                    </Typography>
                </Toolbar>
            </AppBar>
        </div>
    );
}

DashboardAppBar.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(DashboardAppBar);